package com.johnny.boot.openshift.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * This Class is create for OAuth2ServerConfig .
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2ServerConfig extends AuthorizationServerConfigurerAdapter {

	// @Autowired
	// private VoltClientDetailService voltClientDetailService;

	// @Autowired
	// private AuthenticationManager authenticationManager;
	// @Autowired
	// private PasswordEncoder passwordEncoder;

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter jwt = new JwtAccessTokenConverter();
		jwt.setSigningKey("fffffffffff");
		// {
		// @Override
		// public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,
		// OAuth2Authentication authentication) {
		// DefaultOAuth2AccessToken defaultOAuth2AccessToken =
		// (DefaultOAuth2AccessToken) accessToken;
		// Map<String, Object> obj = Maps.newHashMap();
		// obj.putAll(authentication.getOAuth2Request().getExtensions());
		// defaultOAuth2AccessToken.setAdditionalInformation(obj);
		// return super.enhance(defaultOAuth2AccessToken, authentication);
		// }
		// };
		// DefaultAccessTokenConverter defaultAccessTokenConverter = new
		// DashurTokenConverter();
		// defaultAccessTokenConverter
		// .setUserTokenConverter(new DashurUserAuthenticationConverter());
		// jwt.setAccessTokenConverter(defaultAccessTokenConverter);
		return jwt;
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer)
			throws Exception {
		oauthServer.tokenKeyAccess("isAnonymous() || hasAuthority('ADMIN')")
				.checkTokenAccess("hasAuthority('ADMIN')"); //
		// oauthServer.passwordEncoder(passwordEncoder);
	}

	// @Override
	// public void configure(AuthorizationServerEndpointsConfigurer endpoints)
	// throws Exception {
	// endpoints.requestFactory(new
	// DefaultOAuth2RequestFactory(voltClientDetailService) {
	// // add additional information in token
	// @Override
	// public OAuth2Request createOAuth2Request(ClientDetails client,
	// TokenRequest tokenRequest) {
	//
	// OAuth2Request request = super.createOAuth2Request(client, tokenRequest);
	// client.getAdditionalInformation().entrySet()
	// .forEach(entry -> request.getExtensions().put(entry.getKey(),
	// (Serializable) entry.getValue()));
	// return request;
	// }
	// });
	// endpoints.authenticationManager(authenticationManager).accessTokenConverter(accessTokenConverter());
	// }

	// @Override
	// public void configure(ClientDetailsServiceConfigurer clients)
	// throws Exception {
	// clients.withClientDetails(voltClientDetailService);
	// }

}