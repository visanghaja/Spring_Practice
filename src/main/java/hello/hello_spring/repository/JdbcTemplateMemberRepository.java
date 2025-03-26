package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateMemberRepository(DataSource dataSource) {
        this.jdbcTemplate = new  JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        // Spring JDBC를 사용하여 데이터베이스에 Member 객체를 저장하는 메소드이다.
        // SimpleJdbcInsert 를 활용하여 SQL INSERT 문을 직접 작성하지 않고 편리하게 데이터 삽입 가능!

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        // SimpleJdbcInsert는 Spring JDBC에서 INSERT 쿼리를 간편하게 실행할 수 있도록 제공하는 클래스
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");
        // withTableName("member"): 데이터를 삽입할 테이블 이름을 지정 (member 테이블).
        //usingGeneratedKeyColumns("id"): 자동 증가되는 기본 키 (PK) 컬럼을 설정

        Map<String, Object> parameters = new HashMap<>();
        // 삽입할 데이터를 Map 형태로 저장
        parameters.put("name", member.getName());

        Number Key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        // executeAndReturnKey()를 호출하여 데이터를 삽입하고 생성된 기본 키 (id)를 반환한다.
        //MapSqlParameterSource를 사용해 파라미터를 전달.
        member.setId(Key.longValue());
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name);
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member", memberRowMapper());
    }

//    private RowMapper<Member> memberRowMapper() {
//        return new RowMapper<Member>() {
//            @Override
//            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
//
//                Member member = new Member();
//                member.setId(rs.getLong("id"));
//                member.setName(rs.getString("name"));
//                return member;
//            }
//        }
//    }

    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> {
            // alt + enter 해서 람다로 변경

            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));
            return member;
        };
    }
}
