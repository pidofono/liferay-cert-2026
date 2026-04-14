package com.liferaycert.ai.client.dto.v1_0;

import com.liferaycert.ai.client.function.UnsafeSupplier;
import com.liferaycert.ai.client.serdes.v1_0.AiAskRequestSerDes;

import jakarta.annotation.Generated;

import java.io.Serializable;

import java.util.Objects;

/**
 * @author pablo.iglesias
 * @generated
 */
@Generated("")
public class AiAskRequest implements Cloneable, Serializable {

	public static AiAskRequest toDTO(String json) {
		return AiAskRequestSerDes.toDTO(json);
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public void setQuestion(
		UnsafeSupplier<String, Exception> questionUnsafeSupplier) {

		try {
			question = questionUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String question;

	@Override
	public AiAskRequest clone() throws CloneNotSupportedException {
		return (AiAskRequest)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof AiAskRequest)) {
			return false;
		}

		AiAskRequest aiAskRequest = (AiAskRequest)object;

		return Objects.equals(toString(), aiAskRequest.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return AiAskRequestSerDes.toJSON(this);
	}

}