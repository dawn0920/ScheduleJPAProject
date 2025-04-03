# ScheduleJPAProject
내일배움캠프 [Spring 6기] CH 3 일정 관리 앱 Develop

# 내일배움캠프의 세번째 과제

## 개발 기간🕰️
* 25.03.31 ~ 25.04.04



## 필수기능
### Lv0
  API 명세 및 ERD 작성  

  [✔️]  API 명세서 작성하기
  


| 기능                 | Method | URL                                   | request                                                                                 | response                          | 상태코드             |
|---------------------|--------|--------------------------------------|----------------------------------------------------------------------------------------|-----------------------------------|----------------------|
| 회원 가입           | POST   | /users/signup                        | `{ "name": "test", "email": "test@test", "password": "1234" }`                          | 생성된 사용자 정보                 | 201: 정상 등록       |
| 회원 조회           | GET    | /users/{user_id}                     | 요청 파라미터: `user_id`                                                               | 사용자 정보 (`UserResponseDto`)   | 200: 정상 조회       |
| 비밀번호 수정       | PATCH  | /users/{user_id}                     | `{ "oldPassword": "1234", "newPassword": "1111" }`                                     | 수정된 사용자 정보                 | 200: 정상 수정       |
| 회원 삭제           | DELETE | /users/{user_id}                     | 요청 파라미터: `user_id`                                                               | 없음                              | 204: 정상 삭제       |
| 로그인             | POST   | /users/login                         | `{ "email": "test@test", "password": "1111" }`                                         | 로그인 성공 여부                   | 200: 정상 로그인     |
| 로그아웃           | POST   | /users/logout                        | 없음                                                                                   | 로그아웃 성공 여부                 | 200: 정상 로그아웃   |
| 일정 등록          | POST   | /schedules/create                    | `{ "title": "test", "contents": "test", "name": "test" }`                              | 생성된 일정 정보                   | 201: 정상 등록       |
| 일정 단건 조회      | GET    | /schedules/{schedule_id}             | 요청 파라미터: `schedule_id`                                                           | 일정 정보 (`ScheduleResponseDto`) | 200: 정상 조회       |
| 일정 수정          | PATCH  | /schedules/update/{schedule_id}      | `{ "title": "제목", "contents": "내용" }`                                             | 수정된 일정 정보                   | 200: 정상 수정       |
| 일정 삭제          | DELETE | /schedules/delete/{schedule_id}      | 요청 파라미터: `schedule_id`                                                           | 없음                              | 204: 정상 삭제       |
| 댓글 생성          | POST   | /schedules/{schedule_id}             | `{ "content": "댓글입니다." }`                                                         | 생성된 댓글 정보                   | 201: 정상 등록       |
| 댓글 조회          | GET    | /schedules/comment/{schedule_id}     | 요청 파라미터: `schedule_id`                                                           | 댓글 목록 (`List<CommentDto>`)    | 200: 정상 조회       |
| 댓글 수정          | PATCH  | /schedules/comment/{schedule_id}/{comment_id} | `{ "content": "수정된 확인용 입니다." }`                                  | 수정된 댓글 정보                   | 200: 정상 수정       |
| 댓글 삭제          | DELETE | /schedules/comment/{schedule_id}/{comment_id} | 요청 파라미터: `schedule_id`, `comment_id`                                 | 없음                              | 204: 정상 삭제       |
| 일정 목록 조회 (페이징) | GET    | /schedules?page=0&size=10&sort=modifiedAt,desc | 요청 파라미터: `page`, `size`, `sort` (정렬 기준)                         | 일정 목록 (`List<ScheduleResponseDto>`) | 200: 정상 조회       |



  
  [✔️]  ERD 작성하기

  ![Image](https://github.com/user-attachments/assets/0cabe48b-1a9c-4e85-bfec-9a1ab6e80730)
  
    

---
  
### Lv1
  일정 CRUD
  
  [✔️]  일정을 생성, 조회, 수정, 삭제할 수 있음
  
    - 작성 유저명, 할일 제목, 할일 내용, 작성일, 수정일 필드
    
    - 작성일, 수정일 필드는 JPA Auditing을 활용


---

### Lv2
  유저 CRUD
  
  [✔️]  유저를 생성, 조회, 수정, 삭제할 수 있음

    - 유저명, 이메일, 작성일 , 수정일 필드
    
    - 작성일, 수정일 필드는 JPA Auditing을 활용
    
    
  [✔️]  연관관계 구현
  
    -  일정은 이제 작성 유저명 필드 대신 유저 고유 식별자 필드를 가짐
  
  
---

### Lv3
  회원가입
  
  [✔️]  유저에 비밀번호 필드를 추가
  
  
---

### Lv4
  로그인(인증)
  
  [✔️]  Cookie/Session을 활용해 로그인 기능을 구현
    
    
  [✔️]  필터를 활용해 인증 처리
  

  [✔️]  @Configuration 을 활용해 필터를 등록

    - 조건
    
      이메일과 비밀번호를 활용해 로그인 기능을 구현

      회원가입, 로그인 요청은 인증 처리에서 제외


  [✔️]  예외처리

    로그인 시 이메일과 비밀번호가 일치하지 않을 경우 HTTP Status code 401을 반환
  
---

  
## 도전기능
### Lv5
  다양한 예외처리 적용하기
  
  [✔️]  Validation을 활용해 다양한 예외처리를 적용
    
  [✔️]  조건
  
      - 정해진 예외처리 항목이 있는것이 아닌 프로젝트를 분석하고 예외사항을 지정
        - Ex) 할일 제목은 10글자 이내, 유저명은 4글자 이내
      - @Pattern을 사용해서 회원 가입 Email 데이터 검증 등
    
---

### Lv6
  비밀번호 암호화
    
  [✔️]  v.3에서 추가한 비밀번호 필드에 들어가는 비밀번호를 암호화

---

### Lv7
  댓글 CRUD
    
  [✔️]  댓글과 일정은 연관관계를 갖음

  [✔️]  댓글을 저장, 조회, 수정, 삭제할 수 있음

  [✔️]  댓글은 아래와 같은 필드를 갖음

      - 댓글 내용, 작성일, 수정일, 유저 고유 식별자, 일정 고유 식별자 필드
      - 작성일, 수정일 필드는 JPA Auditing을 활용하여 적용

---

### Lv8
  일정 페이징 조회
    
  [✔️]  일정을 Spring Data JPA의 Pageable과 Page 인터페이스를 활용하여 페이지네이션을 구현

      - 페이지 번호와 페이지 크기를 쿼리 파라미터로 전달하여 요청하는 항목을 나타냄
      - 할일 제목, 할일 내용, 댓글 개수, 일정 작성일, 일정 수정일, 일정 작성 유저명 필드를 조회
      - 디폴트 페이지 크기는 10으로 적용
      - 일정의 수정일을 기준으로 내림차순 정렬

---

  
  
