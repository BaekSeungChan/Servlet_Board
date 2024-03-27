```sql
-- members 테이블 생성
CREATE TABLE members (
    membernum INT AUTO_INCREMENT PRIMARY KEY,
    userid VARCHAR(50) NOT NULL UNIQUE,
    username VARCHAR(50) NOT NULL,
    userpassword VARCHAR(50) NOT NULL,
    address VARCHAR(50) NOT NULL,
    phone VARCHAR(30) NOT NULL,
    gender VARCHAR(20) NOT NULL
);

-- hobby 테이블 생성
CREATE TABLE hobby (
    hobynum INT AUTO_INCREMENT PRIMARY KEY,
    hobbyname VARCHAR(50) UNIQUE
);

-- memberhobby 테이블 생성 및 외래 키 설정
CREATE TABLE memberhobby (
    membernum INT,
    hobbyname VARCHAR(50),
    FOREIGN KEY (membernum) REFERENCES members(membernum) ON DELETE CASCADE,
    FOREIGN KEY (hobbyname) REFERENCES hobby(hobbyname) ON DELETE CASCADE,
    PRIMARY KEY (membernum, hobbyname)
);
```