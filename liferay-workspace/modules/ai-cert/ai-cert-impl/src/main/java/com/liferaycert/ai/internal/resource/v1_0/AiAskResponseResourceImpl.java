package com.liferaycert.ai.internal.resource.v1_0;

import com.liferay.portal.configuration.module.configuration.ConfigurationProvider;
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

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author pablo.iglesias
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/ai-ask-response.properties",
	scope = ServiceScope.PROTOTYPE, service = AiAskResponseResource.class
)
@Generated("")
public class AiAskResponseResourceImpl extends BaseAiAskResponseResourceImpl {

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

		String question = aiAskRequest.getQuestion();

		String body = """
			{
			  "model": %s,
			  "input": %s
			}
			""".formatted(toJsonString(model), toJsonString(question));

		HttpRequest request = HttpRequest.newBuilder()
			.uri(URI.create("https://api.openai.com/v1/responses"))
			.header("Authorization", "Bearer " + apiKey)
			.header("Content-Type", "application/json")
			.POST(HttpRequest.BodyPublishers.ofString(body))
			.build();

		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> response = client.send(
			request, HttpResponse.BodyHandlers.ofString());

		AiAskResponse aiAskResponse = new AiAskResponse();

		aiAskResponse.setAnswer(response.body());

		return aiAskResponse;
	}

	private String toJsonString(String value) {
		return "\"" + value.replace("\"", "\\\"") + "\"";
	}

	@Reference
	private ConfigurationProvider _configurationProvider;

}
