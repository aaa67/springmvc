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

	// 1. Create: ?Éà Î∂??Ñú ?Éù?Ñ±
	public int deptInsert(DeptDTO dept) {
		return deptDAO.deptInsert(dept);
	}

	// 2. Read: Î∂??Ñú Î™®Îëê Ï°∞Ìöå
	public List<DeptDTO> selectAll() {
		return deptDAO.selectAll();
	}

	// 3. Update: Î∂??Ñú ?†ïÎ≥? ?àò?†ï
	public int deptUpdate(DeptDTO dept) {
		return deptDAO.deptUpdate(dept);
	}

	// 4. Delete: ?äπ?†ï Î∂??Ñú ?Ç≠?†ú
	public int deptDelete(int deptid) {
		return deptDAO.deptDelete(deptid);
	}
	
	//?äπ?†ï Î∂??Ñú Ï°∞Ìöå
	public DeptDTO selectById(int deptid) {
		return deptDAO.selectById(deptid);
	}

}
