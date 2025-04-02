package org.example.schedulejpaproject.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {
    // 인증 없이 접근 가능한 URL 리스트 (화이트리스트)
    private static final String[] WHITE_LIST = {"/", "/users/signup", "/users/login"};

    // 인증을 하지 않아도 될 URL PATH 배열
    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        // 다양한 기능을 사용하기 위해 다운 캐스팅
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        // 다양한 기능을 사용하기 위해 다운 캐스팅
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        log.info("로그인 필터 로직 실행");

        // 로그인 확인 -> session이 존재 하면 가져옴 없으면 null
        if (!isWhitList(requestURI)) {
            HttpSession session = httpRequest.getSession(false);

            // 로그인 하지 않은 사용자의 경우
            if (session == null || session.getAttribute("user") == null) {
                throw  new RuntimeException("로그인 해주세요.");
            }

            log.info("로그인 된 사용자 요청 허용 : {}", requestURI);
        }

        // 1번경우 : whiteListURL에 등록된 URL 요청이면 바로 chain.doFilter()
        // 2번경우 : 필터 로직 통과 후 다음 필터 호출 chain.doFilter()
        // 다음 필터 없으면 Servlet -> Controller 호출
        chain.doFilter(request, response);
    }

    // 로그인 여부를 확인하는 URL인지 체크 메서드
    private boolean isWhitList(String requestURI) {
        // request URI가 whiteListURL에 포함되는지 확인
        // 포함되면 true 반환
        // 포함되지 않으면 false 반환
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
