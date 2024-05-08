# <p align="center">be05-4th-5team-Chicken-KnowGet</p>


<p align="center">
  ✨ 백지환 서근엽 정유진 정민지 ✨
  Chicken team 의 KnowGet 프로젝트
</p>

<p align="center">
  <img src="https://github.com/beyond-sw-camp/be05-4th-5team-Chicken-KnowGet/assets/93907076/cdd587dc-ec5e-49db-8d25-4cf4a91ad813"  width=400" height="400"/>
</p>

## 🏡 프로젝트 소개

1. 목적 : <br/> <br/>
          "KnowGet"은 "Knowledge"와 "Get"이라는 두 단어를 결합한 합성어로, 지식을 얻는 과정을 즐겁고 손쉽게 만들어주는 웹 서비스이다.<br/>
          사용자들은 서로에게 질문을 하고 다양한 주제에 대해 답변을 제공함으로써 상호 소통하고 지식을 공유할 수 있다.<br/>
          이 웹서비스를 통해 사용자들은 다른 사람들의 경험과 지식을 공유하며 더욱 풍부한 정보를 얻을 수 있다.<br/>
          <br/> <br/>
          
2. 구현 기능 : <br/> <br/>
   2 - 1) 회원 가입, 로그인 인증 <br/>
   2 - 2) Q&A 게시판 <br/>
   2 - 3) 댓글 <br/>
<br/>

### ⚙️개발 기간

* 2024.04.24(수) ~ 2024.05.08(수) <br/> <br/> <br/>


## 🛠️ 기술 스택
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
<img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white">
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
<img src="https://img.shields.io/badge/Spring Security-6DB33F?style=for-the-badge&logo=Spring Security&logoColor=white">
<br>
<img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=HTML5&logoColor=white">
<img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=CSS3&logoColor=white">
<img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=JavaScript&logoColor=white">
<img src="https://img.shields.io/badge/vue.js-4FC08D?style=for-the-badge&logo=vue.js&logoColor=white">
<br>
<img src="https://img.shields.io/badge/mariaDB-003545?style=for-the-badge&logo=mariaDB&logoColor=white">
<img src="https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white">
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
<img src="https://img.shields.io/badge/jenkins-%232C5263.svg?style=for-the-badge&logo=jenkins&logoColor=white">
<br/> <br/>

## 팀 소개
<table>
  <tbody>
    <tr>
      <td align="center"><a href=""><img src="" width="250px;" height="200px;" alt=""/><br /><sub><b>백지환</b></sub></a><br /></td>
      <td align="center"><a href=""><img src="" width="250px;" height="200px" alt=""/><br /><sub><b>서근엽</b></sub></a><br /></td>
      <td align="center"><a href=""><img src="" width="250px;" height="200px;" alt=""/><br /><sub><b>정민지</b></sub></a><br /></td>
      <td align="center"><a href=""><img src="" width="250px;" height="200px;" alt=""/><br /><sub><b>정유진</b></sub></a><br /></td>
    </tr>
  </tbody>
</table>
<br/><br/>

<br/>
<br/>

## 📦 디렉토리 구조 ( FrontEnd / BackEnd  )

1 ) FrontEnd 디렉토리 구조

```
knowget
├─ .gitignore
├─ babel.config.js
├─ jsconfig.json
├─ package-lock.json
├─ package.json
├─ public
│  ├─ favicon.ico
│  └─ index.html
├─ README.md
├─ src
│  ├─ App.vue
│  ├─ assets
│  │  └─ logo.png
│  ├─ components
│  │  └─ HelloWorld.vue
│  ├─ main.js
│  ├─ router
│  │  └─ index.js
│  ├─ store
│  │  └─ index.js
│  └─ views
│     ├─ AboutView.vue
│     ├─ HomeView.vue
│     ├─ QnADetail.vue
│     ├─ QnAList.vue
│     ├─ QnAWrite.vue
│     ├─ SignUp.vue
│     └─ UserLogin.vue
└─ vue.config.js

```

2 ) BackEnd 디렉토리 구조


```
KnowGet
├─ .git
│  ├─ config
│  ├─ description
│  ├─ FETCH_HEAD
│  ├─ HEAD
│  ├─ hooks
│  │  ├─ applypatch-msg.sample
│  │  ├─ commit-msg.sample
│  │  ├─ fsmonitor-watchman.sample
│  │  ├─ post-update.sample
│  │  ├─ pre-applypatch.sample
│  │  ├─ pre-commit.sample
│  │  ├─ pre-merge-commit.sample
│  │  ├─ pre-push.sample
│  │  ├─ pre-rebase.sample
│  │  ├─ pre-receive.sample
│  │  ├─ prepare-commit-msg.sample
│  │  ├─ push-to-checkout.sample
│  │  ├─ sendemail-validate.sample
│  │  └─ update.sample
│  ├─ info
│  │  └─ exclude
│  ├─ objects
│  │  ├─ info
│  │  └─ pack
│  └─ refs
│     ├─ heads
│     └─ tags
├─ .gitignore
├─ gradle
│  └─ wrapper
│     ├─ gradle-wrapper.jar
│     └─ gradle-wrapper.properties
├─ gradlew
├─ gradlew.bat
└─ src
   ├─ main
   │  ├─ java
   │  │  └─ com
   │  │     └─ knowget
   │  │        └─ knowget
   │  │           ├─ domain
   │  │           │  ├─ comment
   │  │           │  │  ├─ controller
   │  │           │  │  │  └─ CommentController.java
   │  │           │  │  ├─ dto
   │  │           │  │  │  ├─ CommentDeleteDto.java
   │  │           │  │  │  ├─ CommentRequestDto.java
   │  │           │  │  │  ├─ CommentResponseDto.java
   │  │           │  │  │  └─ CommentUpdateDto.java
   │  │           │  │  ├─ repository
   │  │           │  │  │  └─ CommentRepository.java
   │  │           │  │  └─ service
   │  │           │  │     ├─ CommentService.java
   │  │           │  │     └─ CommentServiceImpl.java
   │  │           │  ├─ likes
   │  │           │  │  ├─ controller
   │  │           │  │  │  └─ empty.file
   │  │           │  │  ├─ dto
   │  │           │  │  │  └─ empty.file
   │  │           │  │  ├─ repository
   │  │           │  │  │  └─ empty.file
   │  │           │  │  └─ service
   │  │           │  │     └─ empty.file
   │  │           │  ├─ post
   │  │           │  │  ├─ controller
   │  │           │  │  │  └─ QnaController.java
   │  │           │  │  ├─ dto
   │  │           │  │  │  ├─ QnaModifyRequestDTO.java
   │  │           │  │  │  └─ QnaRequestDTO.java
   │  │           │  │  ├─ exception
   │  │           │  │  │  ├─ InvalidLoginException.java
   │  │           │  │  │  └─ QnaNotFoundException.java
   │  │           │  │  ├─ repository
   │  │           │  │  │  └─ PostRepository.java
   │  │           │  │  └─ service
   │  │           │  │     └─ QnaService.java
   │  │           │  └─ user
   │  │           │     ├─ controller
   │  │           │     │  ├─ MailController.java
   │  │           │     │  └─ UserController.java
   │  │           │     ├─ dto
   │  │           │     │  ├─ EmailCheckDTO.java
   │  │           │     │  ├─ EmailRequestDTO.java
   │  │           │     │  ├─ UserSignInDTO.java
   │  │           │     │  └─ UserSignUpDTO.java
   │  │           │     ├─ exception
   │  │           │     │  ├─ InvalidPasswordException.java
   │  │           │     │  └─ UserNotFoundException.java
   │  │           │     ├─ repository
   │  │           │     │  └─ UserRepository.java
   │  │           │     ├─ service
   │  │           │     │  ├─ MailService.java
   │  │           │     │  └─ UserService.java
   │  │           │     └─ SignInResponse.java
   │  │           ├─ global
   │  │           │  ├─ common
   │  │           │  │  └─ BaseTime.java
   │  │           │  ├─ config
   │  │           │  │  ├─ email
   │  │           │  │  │  └─ EmailConfig.java
   │  │           │  │  ├─ redis
   │  │           │  │  │  └─ RedisUtil.java
   │  │           │  │  ├─ security
   │  │           │  │  │  ├─ JwtAuthenticationFilter.java
   │  │           │  │  │  ├─ SecurityConfig.java
   │  │           │  │  │  └─ TokenProvider.java
   │  │           │  │  └─ swagger
   │  │           │  │     └─ SwaggerConfig.java
   │  │           │  └─ entity
   │  │           │     ├─ Comment.java
   │  │           │     ├─ Likes.java
   │  │           │     ├─ Post.java
   │  │           │     └─ User.java
   │  │           └─ KnowGetApplication.java
   │  ├─ resources
   │  │  ├─ application.properties
   │  │  ├─ jwt.yml
   │  │  └─ templates
   │  │     └─ empty.file
   │  └─ webapp
   │     └─ WEB-INF
   │        └─ views
   │           └─ empty.file
   └─ test
      └─ java
         └─ com
            └─ knowget
               └─ knowget
                  └─ KnowGetApplicationTests.java

```


<br/>

## 📦 시스템 아키텍쳐
![IMG_0004](https://github.com/beyond-sw-camp/be05-4th-5team-Chicken-KnowGet/assets/155809042/2551783e-8931-4612-8d04-db3aadf5baf9)

## ERD
![ERD](https://github.com/beyond-sw-camp/be05-4th-5team-Chicken-KnowGet/assets/155809042/eabf7700-9951-4cfd-b16e-eb8dc076e981)

## WBS
[ WBS ](https://docs.google.com/spreadsheets/d/1c2KiFvD5wSIaPKdhtbI_LOJN7vkrVptiZ7Lq6WQYQkA/edit#gid=1834290736)

![WBS_KnowGet](https://github.com/beyond-sw-camp/be05-4th-5team-Chicken-KnowGet/assets/155809042/c282f9f1-5366-4313-9225-fa426e9057be)

## 요구사항 명세서
[ 요구사항 명세서 ](https://docs.google.com/spreadsheets/d/1c2KiFvD5wSIaPKdhtbI_LOJN7vkrVptiZ7Lq6WQYQkA/edit#gid=1834290736)

![요구사항 명세서_KnowGet](https://github.com/beyond-sw-camp/be05-4th-5team-Chicken-KnowGet/assets/155809042/9002e2ee-02b4-4795-be4e-c59058564d18)

## API 명세서

[ API 명세서 ](https://docs.google.com/spreadsheets/d/1c2KiFvD5wSIaPKdhtbI_LOJN7vkrVptiZ7Lq6WQYQkA/edit#gid=359725514)

![API명세서_KnowGet](https://github.com/beyond-sw-camp/be05-4th-5team-Chicken-KnowGet/assets/155809042/e7a80c59-46e4-4e80-ab93-d5831a6691ea)

<br/>
<br/>

