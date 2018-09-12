package co.uk.eclair.viagami.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;


@Service
public class FacebookService
{
	@Value("${spring.social.facebook.appId}")
	private String AppId;

	@Value("${spring.social.facebook.appSecret}")
	private String Secret;

	private String accessToken;

	public String createFacebookAuthorizationURL(){
		FacebookConnectionFactory fbConnectionFactory = new FacebookConnectionFactory(AppId, Secret);
		OAuth2Operations oauth = fbConnectionFactory.getOAuthOperations();
		OAuth2Parameters parameters = new OAuth2Parameters();
		parameters.setRedirectUri("http://localhost:4000/fb/facebook");
		parameters.setScope("public_profile,email,user_birthday");
		return oauth.buildAuthorizeUrl(parameters);
	}

	public String createFacebookAccessToken(String code) {
		FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory(AppId, Secret);
		AccessGrant accessGrant = connectionFactory
				.getOAuthOperations().exchangeForAccess(code, "http://localhost:4000/fb/facebook", null);
		accessToken = accessGrant.getAccessToken();
		return accessToken;
	}

	public String getName(){
		Facebook facebook = new FacebookTemplate(accessToken);
		String[] fields = {"id", "name"};
		return facebook.fetchObject("me", String.class, fields);
	}
}
