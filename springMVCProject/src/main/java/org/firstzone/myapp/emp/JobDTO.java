package org.firstzone.myapp.emp;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JobDTO {
	private String job_id;
	private String job_title;
	private int min_salary;
	private int max_salary;
}
