package com.liferaycert.ai.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * OSGi configuration scoped to a site (group).
 *
 * Configure per site at:
 *   Control Panel → Configuration → System Settings → AI Cert
 *   (select scope "Site" and choose the target site)
 *
 * @author pablo.iglesias
 */
@ExtendedObjectClassDefinition(
	category = "ai-cert",
	scope = ExtendedObjectClassDefinition.Scope.GROUP
)
@Meta.OCD(
	id = "com.liferaycert.ai.internal.configuration.AiCertGroupConfiguration",
		localization = "content/Language",
		name = "ai-cert-configuration-name"

)
public interface AiCertGroupConfiguration {

	@Meta.AD(
		deflt = "",
		description = "ai-cert-api-key-description",
		name = "ai-cert-api-key-name",
		required = false
	)
	String apiKey();

	@Meta.AD(
		deflt = "gpt-4.1-mini",
		description = "ai-cert-model-description",
		name = "ai-cert-model-name",
		required = false
	)
	String model();

}
