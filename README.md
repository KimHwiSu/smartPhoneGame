# Boss Killer

## 게임 컨셉
보스전만 있는 로그라이크 액션 게임  
잡몹 파밍과 같은 요소 배제

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
보스의 그로기 패턴 파훼 후 딜링
혹은 패턴 사이사이 추가 딜링
보스별 플레이타임은 약 5~7분 예상


## 구현 내용  
### UI 구성
![KakaoTalk_20220609_125609590](https://user-images.githubusercontent.com/58390829/172761214-e0410354-d22a-46bb-8976-cb9f571ad1f4.jpg)
1. 플레이어 체력
2. 보스 체력
3. 좌, 우 이동
4. 공격, 점프, 구르기  

### 플레이어 구현
- 좌우 이동 (맵 밖으로 벗어나지 않음)  
- 공격  
: 공격시 AttackEffect 생성, 몹과의 충돌 검사  
- 점프  
: 점프 시전 동작 이후 점프   
: 점프 시전 동작 중엔 이동 불가, 점프 중엔 이동 가능  
- 구르기  
: 구르기시 무적판정  
- 피격  
: 모든 행동보다 피격판정이 우선  
: 피격 모션동안은 무적판정  

### 보스 구현
- 인간형  
: 좌우 이동   
: 공격  
: 피격  
-> 피격 모션 X, 피격 쿨타임 2초 존재  

### 보스 패턴
- 인간형  
-> 추적  
: 플레이어가 공격 범위에 들어올 때가지 플레이어를 쫓아감  
-> 일반 공격  
: 공격 범위 내 플레이어가 있으면 일반 공격 시전  
: 쿨타임 3초

### 사용된 기술
- Sprite  
: 좌우 반전이 dstRect의 left, right 값 반전으로는 작동하지 않아 행렬곱을 통해 이미지를 반전 시킨 후, AnimSprite의 경우 Index를 거꾸로 세게 함
- Animation  
: sheet sprite가 아닌 AnimSprite와 state를 사용하여 state가 변할 때마다 bitmap을 교체해줌
- AttackEffect(collision)  
: 플레이어와 몬스터의 충돌이 아닌 플레이어의 AttackEffect와 몹, 몹의 AttackEffect와 플레이어의 충돌을 체크함

### 미구현 요소
- Title, Ending Scene  
- BackGround  
: 맵을 조금 더 크게 만들어서 vertscoll을 추가
- 다양한 보스 및 패턴
: 보스 종류 최소 2종, 패턴 5가지 이상 구현

### 게임을 완성한다면 추가하고 싶은 요소
- 여러 보스가 추가되면 보스 클리어마다 보상 아이템을 증정하여 능력치 상승  
: 로그라이크 요소 추가  

### 버그 요소
- reverseBitmap
: 현재 모든 Sprite는 우방향을 default로 제작
: reverse(좌방향) 상태에서 frame update에 문제가 발생하여 애니메이션이 비정상으로 출력될 때가 있음
- 유저 피격
: 유저 상태에서 피격을 우선시하기 때문에 점프 중 피격시 점프 높이 그자리에서 피격모션 재생 및 피격 종료후에도 그 자리에 있음
- Btn
: 가끔 버튼을 눌렀다 뗐을 때, 버튼이 계속 눌려져있는 상태로 있을 때가 있음

##### 개발 일정
|주차|내용|상세|
|---|---|---|
|1주|리소스 제작|UI, 플레이어블 캐릭터|
|2주|게임 프레임 워크 제작||
|3주|플레이어블 캐릭터 개발|기본 조작|
|4주|몹 패턴 디자인 및 개발|공격 패턴 및 행동양식|
|5주|몹 패턴 디자인 및 개발|
|6주|몹 패턴 디자인 및 개발|
|7주|몹 패턴 디자인 및 개발|
|8주|설정 옵션 개발|BGM, 효과음 사운드 조절|
|9주|피드백 및 버그 수정|

##### 실 개발 일정
|주차|내용|상세|
|---|---|---|
|1주|리소스 제작|UI, 플레이어블 캐릭터|
|2주|게임 프레임 워크 제작||
|3주|플레이어블 캐릭터 개발|기본 조작|
|4주|플레이어블 캐릭터 개발|상태 구현|
|5주|몹 패턴 디자인 및 개발|몹 리소스 제작|
|6주|몹, 캐릭터 개발|상태, 충돌 구현|
|7주|몹, 캐릭터 개발|attack1, AttackEffect 리소스 제작|
|8주|AttackEffect 충돌 구현|
|9주|몹 개발|mob move 구현|
