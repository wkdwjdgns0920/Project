package com.KoreaIT.jjh.project.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.KoreaIT.jjh.project.vo.Member;

@Mapper
public interface MemberRepository {
	
	
	@Insert("""
			INSERT INTO `member`
			SET regDate = NOW(),
			updateDate = NOW(),
			loginId = #{loginId},
			loginPw = #{loginPw},
			`name` = #{name},
			nickname = #{nickname},
			cellphoneNum = #{cellphoneNum},
			email = #{email}
			""")
	void join(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email);
	//	회원가입을 실행
	
	@Select("""
			SELECT *
			FROM `member`
			WHERE id = #{id}
			""")
	Member getMemberById(int id);
	//	id가 일치하는 회원의 정보를 가져옴

	@Select("""
			SELECT LAST_INSERT_ID()
			""")
	int getLastInsertId();
	//	마지막에 회원가입된 회원의 id를 가져옴

	@Select("""
			SELECT *
			FROM `member`
			WHERE loginId = #{loginId}
			""")
	Member getMemberByLoginId(String loginId);
	//	loginId에 맞는 회원의 정보를 가져옴

	@Select("""
			SELECT *
			FROM `member`
			WHERE name = #{name}
			AND email = #{email}
			""")
	Member getMemberByNameAndEmail(String name, String email);
	//	회원의 이름과 이메일을 통해서 일하는 회원의 정보를 가져옴
	
	
	@Update("""
			<script>
			UPDATE `member`
			SET
				<if test="loginPw != null">
					loginPw = #{loginPw},
				</if>
				<if test="name != null">
					name = #{name},
				</if>
				<if test="nickname != null">
					nickname = #{nickname},
				</if>
				<if test="cellphoneNum != null">
					cellphoneNum = #{cellphoneNum},
				</if>
				<if test="email != null">
					email = #{email},
				</if>
				updateDate= NOW()
			WHERE id = #{actorId}
			</script>
			""")
	int modify(int actorId, String loginPw, String name, String nickname, String cellphoneNum, String email);
	//	회원정보를 수정하고 수정한 행에 대한 정보를 전달	
	
	@Select("""
			<script>
			SELECT COUNT(*) AS cnt
			FROM `member` AS M
			WHERE 1
			<if test="mSearchKeyword != ''">
				<choose>
					<when test="mSearchKeywordTypeCode == 'loginId'">
						AND M.loginId LIKE CONCAT('%', #{mSearchKeyword}, '%')
					</when>
					<when test="mSearchKeywordTypeCode == 'name'">
						AND M.name LIKE CONCAT('%', #{mSearchKeyword}, '%')
					</when>
					<when test="mSearchKeywordTypeCode == 'nickname'">
						AND M.nickname LIKE CONCAT('%', #{mSearchKeyword}, '%')
					</when>
					<otherwise>
						AND (
							M.loginId LIKE CONCAT('%', #{mSearchKeyword}, '%')
							OR M.name LIKE CONCAT('%', #{mSearchKeyword}, '%')
							OR M.nickname LIKE CONCAT('%', #{mSearchKeyword}, '%')
							)
					</otherwise>
				</choose>
			</if>
			</script>
			""")
	int getMembersCount(String mSearchKeywordTypeCode, String mSearchKeyword);
	//	회원수를 가져옴	
	
	@Select("""
			<script>
			SELECT M.*
			FROM `member` AS M
			WHERE 1
			<if test="mSearchKeyword != ''">
				<choose>
					<when test="mSearchKeywordTypeCode == 'loginId'">
						AND M.loginId LIKE CONCAT('%', #{mSearchKeyword}, '%')
					</when>
					<when test="mSearchKeywordTypeCode == 'name'">
						AND M.name LIKE CONCAT('%', #{mSearchKeyword}, '%')
					</when>
					<when test="mSearchKeywordTypeCode == 'nickname'">
						AND M.nickname LIKE CONCAT('%', #{mSearchKeyword}, '%')
					</when>
					<otherwise>
						AND (
							M.loginId LIKE CONCAT('%', #{mSearchKeyword}, '%')
							OR M.name LIKE CONCAT('%', #{mSearchKeyword}, '%')
							OR M.nickname LIKE CONCAT('%', #{mSearchKeyword}, '%')
							)
					</otherwise>
				</choose>
			</if>
			ORDER BY M.id DESC
				<if test="limitTake != -1">
					LIMIT #{limitFrom}, #{limitTake}
				</if>
			</script>
			""")
	List<Member> getForPrintMembers(String mSearchKeywordTypeCode, String mSearchKeyword, int limitFrom,
			int limitTake);
	//	회원정보를 가져옴
	
	@Delete("""
			<script>
			UPDATE `member`
			<set>
				updateDate = NOW(),
				delStatus = 1,
				delDate = NOW(),
			</set>
			WHERE id = #{id}
			</script>
			""")
	void deleteMember(int id);
}
