# Stove Blog
Stove Dev Camp 2번 과제를 위한 레포지토리입니다.
![메인화면](https://user-images.githubusercontent.com/65841596/140411651-1c8f78c8-62b5-4119-85a2-6be51e7a5fd0.jpg)

## :clipboard: 실행방법
별도의 설정 없이 프로젝트 클론 후 실행

## :clipboard: DB 웹 콘솔 실행방법
1. http://localhost:65000/h2-console/ 접속
2. JDBCurl = jdbc:h2:mem:stove 입력 후 연결

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


## :link: 프로젝트를 하면서 고민했던 점
### 프로젝트 구조에 대한 고민

   1. 컨트롤러, DTO, Entity, service로 분할   
   -- Project  
   ---- controller  
   ---- dto  
   ---- entity  
   ---- service  
   ---- config  
      
   문제점 : 후에 새로운 기능과 도메인이 더 추가 될 수록 프로젝트에 대한 전체적인 기능과 구조를 한눈에 파악하기 힘듦  
   
   2. 페이지 단위로 분할   
     -- Project   
     ---- main   
     ---- admin   
     ---- post   
     ---- config  
     
   문제점 : category는 별도의 페이지를 형성하지 않으며, main, admin, post 모두 카테고리 관련 기능을 포함하고 있음. common 디렉터리를 만들고 category를 넣어주는 방법도 있지만, 도메인이 더 추가되고 도메인간 연관 관계가 추가될수록 common디렉터리가 가지는 책임과 크기가 늘어남.
   
   3. 도메인 모듈 단위로 분할   
      --Project   
      ---- infra   
      ------ config   
      ---- modules   
      ------ main   
      ------ admin   
      ------ post   
      ------ category   
      ------ comment   
      
   최종적으로 도메인 모듈 단위로 분할하는 방법을 선택.
   
   
### Post 와 Comment의 방향 관계 설정
현재 Post와 Comment의 관계는 Comment만 Post를 참조하고 있는 단 방향 관계임.
따라서 게시글 조회 화면에서 게시글 정보를 가져오기 위해 2개의 쿼리가 발생.
1. select post from Post where id = ?
1. select comment from Comment where post_id = ?   

반면에 Post가 comment에 대한 참조를 가지고 있을 경우(List<Comment> comments) Post에 대한 쿼리만 수행하여도 게시글 조회 화면에 필요한 모든 정보를 가지고 올 수 있기에 성능상의 이점을 얻을 수 있음.   
하지만 이럴 경우 서로가 서로를 참조하는 순환 참조 구조를 가지게 됨 Post <-> Comments
   
### ISP,SRP,DIP에 대한 고민
현재 PostService는 게시글에 관련된 기능만을 제공하기에 하나의 책임을 가진것처럼 보인다.   
반면에 다른 관점에서 본다면 게시글 생성, 게시글 삭제, 게시글 조회, 게시글 수정 같은 4개의 책임을 가진다고도 볼 수 있다.
이럴 경우 각 기능에 대하여 PostCreateInterface와 구현체, PostDeleteInterface와 구현체, PostQueryInterface와 구현체, PostUpdateInterface와 구현체를 생성하여 PostService가 각 인터페이스를 참조하고 스프링 빈으로 구현체를 주입받는다면 ISP,SRP,DIP를 만족시킬 수 있다.   
하지만 PostService의 기능이 단순함에 비해 4개의 인터페이스와 4개의 구현체를 추가로 작성해야한다.

