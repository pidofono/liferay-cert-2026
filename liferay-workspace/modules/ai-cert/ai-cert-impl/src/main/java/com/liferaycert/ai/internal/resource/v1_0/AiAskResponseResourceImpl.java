package com.liferaycert.ai.internal.resource.v1_0;

import com.liferay.portal.configuration.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import com.liferaycert.ai.dto.v1_0.AiAskRequest;
import com.liferaycert.ai.dto.v1_0.AiAskResponse;
import com.liferaycert.ai.internal.configuration.AiCertGroupConfiguration;
import com.liferaycert.ai.resource.v1_0.AiAskResponseResource;

import jakarta.annotation.Generated;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

import javax.json.JsonObject;

/**
 * @author pablo.iglesias
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/ai-ask-response.properties",
	scope = ServiceScope.PROTOTYPE, service = AiAskResponseResource.class
)
@Generated("")
public class AiAskResponseResourceImpl extends BaseAiAskResponseResourceImpl {

   	Log _log = LogFactoryUtil.getLog(AiAskResponseResourceImpl.class);
	private static final ConcurrentHashMap<String, String> CACHE = new ConcurrentHashMap<>();

	@Override
	public AiAskResponse postAsk(AiAskRequest aiAskRequest) throws Exception {
		long groupId = ParamUtil.getLong(contextHttpServletRequest, "groupId");

		if (groupId == 0) {
			groupId = GroupLocalServiceUtil.getGroup(contextCompany.getCompanyId(),"Guest").getGroupId();
		}

		AiCertGroupConfiguration configuration =
			_configurationProvider.getGroupConfiguration(
				AiCertGroupConfiguration.class, groupId);

		String apiKey = configuration.apiKey();
		String model = configuration.model();

		if (Validator.isBlank(apiKey)) {
			AiAskResponse errorResponse = new AiAskResponse();

			errorResponse.setAnswer(
				"AI Cert is not configured for this site. " +
				"Set the API Key in System Settings → AI Cert.");

			return errorResponse;
		}

		if (aiAskRequest.getQuestion() == null || aiAskRequest.getQuestion().isBlank()) {
			throw new RuntimeException("Question is required");
		}
		if (aiAskRequest.getQuestion().length() > 500) {
			throw new RuntimeException("Question too long");
		}
		String question = aiAskRequest.getQuestion();
		_log.info("Pregunta recibida: " + question);

		if (CACHE.containsKey(question)) {
			AiAskResponse cached = new AiAskResponse();
			cached.setAnswer(CACHE.get(question));
			return cached;
		}


		String body = """
			{
			  "model": %s,
			  "input": %s
			}
			""".formatted(toJsonString(model), toJsonString(question));

		HttpRequest request = HttpRequest.newBuilder()
			.uri(URI.create("https://api.openai.com/v1/responses"))
			.timeout(java.time.Duration.ofSeconds(15))
			.header("Authorization", "Bearer " + apiKey)
			.header("Content-Type", "application/json")
			.POST(HttpRequest.BodyPublishers.ofString(body)).timeout(java.time.Duration.ofSeconds(15))
			.build();

		HttpClient client = HttpClient.newBuilder()
				.connectTimeout(java.time.Duration.ofSeconds(10))
				.build();

		HttpResponse<String> response = client.send(
			request, HttpResponse.BodyHandlers.ofString());

		AiAskResponse aiAskResponse = new AiAskResponse();

		aiAskResponse.setAnswer(response.body());
		JSONObject jAnswer = JSONFactoryUtil.createJSONObject(aiAskResponse.getAnswer());
		String answer = getMessage(jAnswer);
		aiAskResponse.setAnswer(answer);


		return aiAskResponse;
	}

	private String getMessage (JSONObject jAnswer){
		JSONObject jError  = jAnswer.getJSONObject("error");
		String code = jError.getString("code");
		String param = jError.getString("param");
		String message = jError.getString("message");
		String type = jError.getString("type");
		_log.info("Code = " + code);
		_log.info("Message = " + message);
		_log.info("Param = " + param);
		_log.info("Type = " + type);
		return message;
	}

	private String toJsonString(String value) {
		return "\"" + value.replace("\"", "\\\"") + "\"";
	}

	@Reference
	private ConfigurationProvider _configurationProvider;

}
