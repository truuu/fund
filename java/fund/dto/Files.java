package fund.dto;

import org.springframework.web.multipart.MultipartFile;
public class Files {
	private String name;
	private MultipartFile fileData;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public MultipartFile getFileData()
	{
		return fileData;
	}

	public void setFileData(MultipartFile uploadedFile)
	{
		this.fileData = uploadedFile;
	}
}
