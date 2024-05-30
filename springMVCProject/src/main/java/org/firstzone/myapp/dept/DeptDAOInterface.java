package org.firstzone.myapp.dept;

import java.util.List;

public interface DeptDAOInterface {
	
	public int deptInsert(DeptDTO dept);

	public List<DeptDTO> selectAll();

	public DeptDTO selectById(int deptid);

	public List<DeptDTO> selectByName(String deptName);

	public int deptUpdate(DeptDTO dept);
	
	public int deptDelete(int deptid);
}
