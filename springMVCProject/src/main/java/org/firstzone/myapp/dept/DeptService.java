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

	// 1. Create: ? λΆ?? ??±
	public int deptInsert(DeptDTO dept) {
		return deptDAO.deptInsert(dept);
	}

	// 2. Read: λΆ?? λͺ¨λ μ‘°ν
	public List<DeptDTO> selectAll() {
		return deptDAO.selectAll();
	}

	// 3. Update: λΆ?? ? λ³? ?? 
	public int deptUpdate(DeptDTO dept) {
		return deptDAO.deptUpdate(dept);
	}

	// 4. Delete: ?Ή?  λΆ?? ?­? 
	public int deptDelete(int deptid) {
		return deptDAO.deptDelete(deptid);
	}
	
	//?Ή?  λΆ?? μ‘°ν
	public DeptDTO selectById(int deptid) {
		return deptDAO.selectById(deptid);
	}

}
