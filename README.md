# FastCampus self-study
- 패스트캠퍼스 강의 - '나만의 MVC 프레임워크 만들기' 중 chapter5. 'JDBC 프로그래밍' 강의 테스트 코드

## 01. JDBC 개념 소개
### JDBC (Java Database Connectivity)
- 자바 애플리케이션에서 DB 프로그래밍을 할 수 있도록 도와주는 표준 인터페이스 → DBMS 종류와 무관
- JDBC 인터페이스들을 구현한 구현체들은 각 데이터베이스 벤더 사들이 제공 → JDBC 드라이버
  ![](2023-06-28-jdbc-architecture.jpg)
  - 출처: https://tecoble.techcourse.co.kr/post/2023-06-28-JDBC-DataSource/


## 02. DB 커넥션 풀 개념 소개
### DBCP (Database Connection Pool)
- 미리 일정량의 DB 커넥션을 생성해서 풀에 저장해 두고 있다가 HTTP 요청에 따라 필요 시 풀에서 커넥션을 가져다 사용하는 기법
- 참고로 스프링 부트 2.0 부터는 디폴트 커넥션 풀로 HikariCP 사용
- DBCP 라이브러리 종류
  - Hikari CP
  - Apache Commons DBCP
  - Tomcat JDBC Pool

### 커넥션 풀 사용 시 유의 사항
- 커넥션의 사용 주체는 WAS 쓰레드 이므로 커넥션 개수는 WAS 쓰레드 수와 함께 고려해야 함
- 커넥션 수를 크게 설정하면 메모리 소모가 큰 대신 동시 접속자 수가 많아지더라도 사용자 대기 시간이 상대적으로 줄어들게 되고, 반대로 커넥션 개수를 작게 설정하면 메모리 소모는 적은 대신 그만큼 대기 시간이 길어질 수 있음. 따라서 적정량의 커넥션 객체를 생성해 두어야 함

### DataSource
- 커넥션 획득 위해서 Java의 DataSource 를 사용 (표준 인터페이스)
- 실습은 Hikari CP 의 DataSource 사용
  - 출처) https://velog.io/@hyejinjeong9999/커넥션-풀
