## 요구사항 분석

- 문자열은 `기본 구분자`와 `커스텀 구분자`로 분리할 수 있다.
- 기본 구분자는 쉼표 (,) 또는 콜론 ( : ) 이다.
- 커스텀 구분자는 문자열 앞부분의 // 와 \n 사이에 위치하는 문자이다.
- 구분자로 분리한 숫자들의 합을 반환한다.
  - 문자열 내에 공백이 존재하는 경우, 숫자 0 으로 간주한다.
  - 문자열 내에 음수가 존재하는 경우, "문자열 계산기에는 음수가 들어갈 수 없습니다" 메시지를 담은 RuntimeException 으로 예외처리한다.
- 테스트 케이스
  1. 커스텀 구분자가 1개인 경우
     - //;\n1;2;3 의 경우, 커스텀 구분자는 ; 이며, 6을 반환한다.
  2. 커스텀 구분자가 2개 이상인 경우
     - //#\n//@\n1#2@3 인 경우, 커스텀 구분자는 # 과 @ 이며, 6을 반환한다.
  3. 기본 구분자가 아니고, 커스텀 구분자로 설정하지 않은 구분자가 나올 경우
     - //#\n1;2#3 인 경우, 기본 구분자도 아니고 커스텀 구분자도 아닌 ; 구분자가 나왔으므로 "기본 구분자도, 커스텀 구분자도 아닌 구분자입니다" 메시지를 담은 RuntimeException 으로 예외처리한다.
  4. 문자열 내에 공백이 존재하는 경우 숫자 0 으로 간주한다.
     - " ,1,2" 인 경우, 3을 반환한다.
     - " " 인 경우, 0을 반환한다.
  5. 문자열 내에 숫자가 존재하지 않는 경우
     - "//#\n#" 인 경우, "숫자가 존재하지 않습니다" 메시지를 담은 RuntimeException 으로 예외처리한다.
  6. 문자열 내에서 구분자가 숫자보다 앞서 나오는 경우
     - "#3,1" 인 경우 "첫번째 숫자 앞에는 구분자가 존재할 수 없습니다" 메시지를 담은 RuntimeException 으로 예외처리한다.