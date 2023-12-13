package hyundai.cc.exception;


import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {

        // 원하는 로직 수행

        // HTTP 상태 코드 설정
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // 실패한 이유 등을 추가적으로 설정할 수 있습니다.
        // response.getWriter().write("Authentication Failed: " + exception.getMessage());

        // 기본적으로는 authentication-failure-url로 리다이렉션 됩니다.
    }
}
