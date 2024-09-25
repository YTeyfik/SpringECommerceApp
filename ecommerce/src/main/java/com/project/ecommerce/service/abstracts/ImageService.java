package com.project.ecommerce.service.abstracts;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.project.ecommerce.dto.ImageDto;
import com.project.ecommerce.model.Image;

public interface ImageService {
	Image getImageById(Long id);
	void deleteImageById(long id);
	List<ImageDto> saveImages(List<MultipartFile> files, Long ProductId);
	void updateImage(MultipartFile file,Long imageId);
}
