package org.firstzone.myapp.dept;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DeptService {

	@Autowired
	@Qualifier("deptmybatis")
	DeptDAOInterface deptDAO;

	// 1. Create: ?�� �??�� ?��?��
	public int deptInsert(DeptDTO dept) {
		return deptDAO.deptInsert(dept);
	}

	// 2. Read: �??�� 모두 조회
	public List<DeptDTO> selectAll() {
		return deptDAO.selectAll();
	}

	// 3. Update: �??�� ?���? ?��?��
	public int deptUpdate(DeptDTO dept) {
		return deptDAO.deptUpdate(dept);
	}

	// 4. Delete: ?��?�� �??�� ?��?��
	public int deptDelete(int deptid) {
		return deptDAO.deptDelete(deptid);
	}
	
	//?��?�� �??�� 조회
	public DeptDTO selectById(int deptid) {
		return deptDAO.selectById(deptid);
	}

}
