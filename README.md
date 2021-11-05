# Stove Blog
Stove Dev Camp 2번 과제를 위한 레포지토리입니다.
![메인화면](https://user-images.githubusercontent.com/65841596/140411651-1c8f78c8-62b5-4119-85a2-6be51e7a5fd0.jpg)

## :clipboard: 실행방법
1. 프로젝트 클론
2. 프로젝트 터미널에서 ./gradlew build 입력 후 실행

## :clipboard: 개발환경
* IntelliJ
* GitHub

## :clipboard: 사용 기술
### 백엔드
#### Spring boot
* JAVA 11
* Spring MVC
* Spring Data JPA
* QueryDSL

#### Build tool
* Gradle

#### Database
* H2

### 프론트엔드
* Javascript
* Thymeleaf
* jQuery

## :link: ERD 설계
### Entity
- Post : 블로그의 게시글을 나타내는 엔티티
   1. 게시글은 작성자의 정보를 가진다.
   2. 게시글은 내용을 가진다.
   3. 게시글은 생성된 날짜를 가진다.
   4. 게시글은 공개 여부를 가진다.
   5. 게시글은 썸네일을 가진다.
   6. 게시글은 제목을 가진다.
   7. 게시글은 자신이 포함된 카테고리 정보를 가진다.
- Cateogry : 블로그의 게시글 카테고리를 나타내는 엔티티
   1. 카테고리는 카테고리 이름을 가진다.
   2. 카테고리는 생성된 날짜를 가진다.
 
- Comment : 각 게시글에 생성되는 댓글을 나타내는 엔티티
   1. 댓글은 작성자의 정보를 가진다.
   2. 댓글은 내용을 가진다.
   3. 댓글은 작성된 날짜를 가진다.
   4. 댓글은 자신이 작성된 게시글 정보를 가진다.
### Relation
1. 게시글과 카테고리는 1:다 관계이다
   - 게시글은 하나의 카테고리를 가진다.
   - 카테고리는 여러개의 게시글을 가진다.
2. 댓글과 게시글은 1:다 관계이다.
   - 댓글은 하나의 게시글을 가진다.
   - 게시글은 여러개의 댓글을 가진다.

![ERD](https://user-images.githubusercontent.com/65841596/140423482-1fec84e9-95a3-45fe-8cbb-41b214806bb7.png)

