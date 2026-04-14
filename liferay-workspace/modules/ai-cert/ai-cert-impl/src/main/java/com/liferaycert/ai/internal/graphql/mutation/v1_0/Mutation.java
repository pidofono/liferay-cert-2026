package com.liferaycert.ai.internal.graphql.mutation.v1_0;

import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;

import com.liferaycert.ai.dto.v1_0.AiAskRequest;
import com.liferaycert.ai.dto.v1_0.AiAskResponse;
import com.liferaycert.ai.resource.v1_0.AiAskResponseResource;

import jakarta.annotation.Generated;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.ws.rs.core.UriInfo;

import java.util.function.BiFunction;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author pablo.iglesias
 * @generated
 */
@Generated("")
public class Mutation {

	public static void setAiAskResponseResourceComponentServiceObjects(
		ComponentServiceObjects<AiAskResponseResource>
			aiAskResponseResourceComponentServiceObjects) {

		_aiAskResponseResourceComponentServiceObjects =
			aiAskResponseResourceComponentServiceObjects;
	}

	@GraphQLField
	public AiAskResponse createAsk(
			@GraphQLName("aiAskRequest") AiAskRequest aiAskRequest)
		throws Exception {

		return _applyComponentServiceObjects(
			_aiAskResponseResourceComponentServiceObjects,
			this::_populateResourceContext,
			aiAskResponseResource -> aiAskResponseResource.postAsk(
				aiAskRequest));
	}

	private <T, R, E1 extends Throwable, E2 extends Throwable> R
			_applyComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeFunction<T, R, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			return unsafeFunction.apply(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private <T, E1 extends Throwable, E2 extends Throwable> void
			_applyVoidComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeConsumer<T, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			unsafeFunction.accept(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private void _populateResourceContext(
			AiAskResponseResource aiAskResponseResource)
		throws Exception {

		aiAskResponseResource.setContextAcceptLanguage(_acceptLanguage);
		aiAskResponseResource.setContextCompany(_company);
		aiAskResponseResource.setContextHttpServletRequest(_httpServletRequest);
		aiAskResponseResource.setContextHttpServletResponse(
			_httpServletResponse);
		aiAskResponseResource.setContextUriInfo(_uriInfo);
		aiAskResponseResource.setContextUser(_user);
		aiAskResponseResource.setGroupLocalService(_groupLocalService);
		aiAskResponseResource.setRoleLocalService(_roleLocalService);
	}

	private static ComponentServiceObjects<AiAskResponseResource>
		_aiAskResponseResourceComponentServiceObjects;

	private AcceptLanguage _acceptLanguage;
	private com.liferay.portal.kernel.model.Company _company;
	private GroupLocalService _groupLocalService;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
	private RoleLocalService _roleLocalService;
	private BiFunction<Object, String, com.liferay.portal.kernel.search.Sort[]>
		_sortsBiFunction;
	private UriInfo _uriInfo;
	private com.liferay.portal.kernel.model.User _user;

}