package com.liferaycert.ai.client.serdes.v1_0;

import com.liferaycert.ai.client.dto.v1_0.AiAskRequest;
import com.liferaycert.ai.client.json.BaseJSONParser;

import jakarta.annotation.Generated;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author pablo.iglesias
 * @generated
 */
@Generated("")
public class AiAskRequestSerDes {

	public static AiAskRequest toDTO(String json) {
		AiAskRequestJSONParser aiAskRequestJSONParser =
			new AiAskRequestJSONParser();

		return aiAskRequestJSONParser.parseToDTO(json);
	}

	public static AiAskRequest[] toDTOs(String json) {
		AiAskRequestJSONParser aiAskRequestJSONParser =
			new AiAskRequestJSONParser();

		return aiAskRequestJSONParser.parseToDTOs(json);
	}

	public static String toJSON(AiAskRequest aiAskRequest) {
		if (aiAskRequest == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (aiAskRequest.getQuestion() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"question\": ");

			sb.append("\"");

			sb.append(_escape(aiAskRequest.getQuestion()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		AiAskRequestJSONParser aiAskRequestJSONParser =
			new AiAskRequestJSONParser();

		return aiAskRequestJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(AiAskRequest aiAskRequest) {
		if (aiAskRequest == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (aiAskRequest.getQuestion() == null) {
			map.put("question", null);
		}
		else {
			map.put("question", String.valueOf(aiAskRequest.getQuestion()));
		}

		return map;
	}

	public static class AiAskRequestJSONParser
		extends BaseJSONParser<AiAskRequest> {

		@Override
		protected AiAskRequest createDTO() {
			return new AiAskRequest();
		}

		@Override
		protected AiAskRequest[] createDTOArray(int size) {
			return new AiAskRequest[size];
		}

		@Override
		protected boolean parseMaps(String jsonParserFieldName) {
			if (Objects.equals(jsonParserFieldName, "question")) {
				return false;
			}

			return false;
		}

		@Override
		protected void setField(
			AiAskRequest aiAskRequest, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "question")) {
				if (jsonParserFieldValue != null) {
					aiAskRequest.setQuestion((String)jsonParserFieldValue);
				}
			}
		}

	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		for (String[] strings : BaseJSONParser.JSON_ESCAPE_STRINGS) {
			string = string.replace(strings[0], strings[1]);
		}

		return string;
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\": ");

			Object value = entry.getValue();

			sb.append(_toJSON(value));

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

	private static String _toJSON(Object value) {
		if (value == null) {
			return "null";
		}

		if (value instanceof Map) {
			return _toJSON((Map)value);
		}

		Class<?> clazz = value.getClass();

		if (clazz.isArray()) {
			StringBuilder sb = new StringBuilder("[");

			Object[] values = (Object[])value;

			for (int i = 0; i < values.length; i++) {
				sb.append(_toJSON(values[i]));

				if ((i + 1) < values.length) {
					sb.append(", ");
				}
			}

			sb.append("]");

			return sb.toString();
		}

		if (value instanceof String) {
			return "\"" + _escape(value) + "\"";
		}

		return String.valueOf(value);
	}

}