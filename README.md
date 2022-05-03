# Boss Killer

## 게임 컨셉
테라리아와 같은 횡스크롤 RPG 게임에서 보스전만을 채용한 액션 게임  
파밍과 같은 RPG요소 배제

## 개발범위
- 최소 둘 이상의 보스 몬스터  
-> 보스 행동양식
- 캐릭터 조작
- UI
- 리소스 수집 및 제작
- 스테이지 구성

## 예상 실행 흐름
### 전체 흐름
타이틀 -> 첫 보스 대면 씬 -> 전투 -> 보스 엔딩 씬 -> 두번째 보스 대면 씬 ... -> 게임 엔딩  
### 전투 흐름
보스마다 후딜레이가 긴 패턴이 존재해 해당 패턴이후 딜타임을 갖거나  
패턴 중간중간 회피기동으로 추가적으로 짤딜 넣기  
보스별 플레이타임은 약 5~7분 예상
### 비슷한 게임
![image](https://user-images.githubusercontent.com/58390829/160202322-833a5371-701d-4256-870c-141a53883c31.png)
#### 다른 점?  
-> 스킬 X, 구르기 및 점프 회피
### UI 구성
![image](https://user-images.githubusercontent.com/58390829/160200811-01e7136c-eb4c-4794-981f-5c64f195acc2.png)
1. 플레이어 체력
2. 보스 체력
3. 좌, 우 이동
4. 공격, 점프, 구르기  

게임의 시점은 플레이어가 항상 중앙으로 오도록 지정

### 보스 구성 패턴
- 기본 공격
- 강공격
- 외에 각자 고유 패턴이 3~4개 존재
### 보스 디자인
- 인간형
- 비인간형

## 개발 일정

|주차|내용|상세|
|------|---|--|
|1주|리소스 제작|UI, 플레이어블 캐릭터|
|2주|게임 프레임 워크 제작|
|3주|플레이어블 캐릭터 개발|기본 조작|
|4주|몹 패턴 디자인 및 개발|공격 패턴 및 행동양식|
|5주|몹 패턴 디자인 및 개발|
|6주|몹 패턴 디자인 및 개발|
|7주|몹 패턴 디자인 및 개발|
|8주|설정 옵션 개발|BGM, 효과음 사운드 조절|
|9주|피드백 및 버그 수정|
