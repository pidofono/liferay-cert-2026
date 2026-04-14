package com.liferaycert.ai.client.dto.v1_0;

import com.liferaycert.ai.client.function.UnsafeSupplier;
import com.liferaycert.ai.client.serdes.v1_0.AiAskResponseSerDes;

import jakarta.annotation.Generated;

import java.io.Serializable;

import java.util.Objects;

/**
 * @author pablo.iglesias
 * @generated
 */
@Generated("")
public class AiAskResponse implements Cloneable, Serializable {

	public static AiAskResponse toDTO(String json) {
		return AiAskResponseSerDes.toDTO(json);
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public void setAnswer(
		UnsafeSupplier<String, Exception> answerUnsafeSupplier) {

		try {
			answer = answerUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String answer;

	@Override
	public AiAskResponse clone() throws CloneNotSupportedException {
		return (AiAskResponse)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof AiAskResponse)) {
			return false;
		}

		AiAskResponse aiAskResponse = (AiAskResponse)object;

		return Objects.equals(toString(), aiAskResponse.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return AiAskResponseSerDes.toJSON(this);
	}

}