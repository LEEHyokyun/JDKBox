/*
 * 트랜잭션(transaction)
 * - DB에 접근하여 data 조회, 수정 등에 대한 작업단위를 일컫는다.
 * 		ㄴ  "더이상 쪼갤 수 없는 단위" -> 원자성.
 * - DB 형태 및 상태를 변경시키는 것이 핵심
 * - DCL(COMMIT, ROLLBACK)과 관련되어있는 개념.
 * 		ㄴ COMMIT : DB에 작업을 반영, ROLLBACK : 반영취소 및 이전 상태로 회귀
 * 		ㄴ 하나의 작업이라도 오류 발생시 RollBack 대상, 모든 하위작업대상이 완료되어야 COMMIT 대상
 * 		ㄴ try-catch에서 catch 구문에서 rollback, try에서 commit 한다.
 * - 기본적으로 작업단위 모두에 대해 정상완료를 해야하므로, auto commit 해제한다.
 * 		ㄴ set.auotocommit(false)
 * 
 * JDBC에서의 트랜잭션 설정 및 처리
 * - auto commit이 기존설정, 수동 commit으로 설정 -> connection.setAutoCommit(false).
 * - 트랜잭션 세부작업 정상수행 후 connection.commit(), 문제 발생시 connection.rollback()
 * - 
 * try{
 * 	connection.setAutoCommit(false)
 * ..
 * connection.commit
 * }catch{
 * connection.rollback()
 * }finally{
 * close()
 * }
*/
