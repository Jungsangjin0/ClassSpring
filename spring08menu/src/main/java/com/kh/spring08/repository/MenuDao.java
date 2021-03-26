package com.kh.spring08.repository;

import com.kh.spring08.entity.Menu;

//앞으로는 도구를 직접 만들 떄 추상화 구조를 갖도록 구성해야 한다
// - 직접 연결이 아니라 중게하기 때문에 교체가 쉽고 각종 AOP 기능이 적용된다.

//책으로 치면 이 부분이 목차
public interface MenuDao {
	int add(Menu menu);
}
