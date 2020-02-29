package com.cakirilhan.domain.kunde.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.cakirilhan.domain.kunde.repository.DatabaseDateiRepository;
import com.cakirilhan.exception.FileDuplicateException;
import com.cakirilhan.exception.FileStorageException;
import com.cakirilhan.property.FileStorageProperties;

@Service
public class FileStorageService {

	@Autowired
	private DatabaseDateiRepository databaseDateiRepository;
	
	

	private final Path fileStorageLocation;

	@Autowired
	public FileStorageService(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths
				.get(fileStorageProperties.getUploadDir()).toAbsolutePath()
				.normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception e) {
			throw new FileStorageException(
					"Could not create the directory where the uploaded files will be stored.",
					e);
		}
	}

	public boolean deleteFile(String file) {

		Path filePath = this.fileStorageLocation.resolve(file).normalize();

		try {
			Files.delete(filePath);
			return true;
		} catch (IOException e) {

			e.printStackTrace();
			return false;
		}
	}

	public static File convertMultiPartFileToFile(MultipartFile file)
			throws IOException {

		File convFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;

	}

	public boolean storeFile(MultipartFile file) {

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			if (fileName.contains("..")) {
				throw new FileStorageException(
						"Sorry! Filename contains invalid path sequence "
								+ fileName);
			}

			if (databaseDateiRepository.findByFileName(fileName).isPresent()) {

				throw new FileDuplicateException(
						"Sorry diese Datei ist vorhanden. :" + fileName);

			}
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation,
					StandardCopyOption.REPLACE_EXISTING);

			return true;

		} catch (Exception e) {
			throw new FileStorageException("Could not store file " + fileName
					+ ". Please try again!", e);
		}
	}
	
	public Resource loadFile(String fileName) {
		
		try {
			
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			
			Resource resource = new UrlResource(filePath.toUri());
			
			if(resource.exists()) {
				return resource;
			} else {
				throw new FileNotFoundException("Die Datei ist nicht gefunden : " + fileName);
			}
			
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
			return null;

		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			return null;
		}
		
	}

}
