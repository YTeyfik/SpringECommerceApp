package com.project.ecommerce.service.concretes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.ecommerce.dto.ImageDto;
import com.project.ecommerce.exceptions.ResourceNotFoundException;
import com.project.ecommerce.model.Image;
import com.project.ecommerce.model.Product;
import com.project.ecommerce.repository.ImageRepository;
import com.project.ecommerce.service.abstracts.ImageService;
import com.project.ecommerce.service.abstracts.ProductService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ImageManager implements ImageService{
	
	private ImageRepository imageRepository;
	private ProductService productService;
	
	@Override
	public Image getImageById(Long id) {
		// TODO Auto-generated method stub
		return imageRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Image not found wit this id: "+id));
	}

	@Override
	public void deleteImageById(long id) {
		imageRepository.findById(id).ifPresentOrElse(imageRepository :: delete, ()->{
			throw new ResourceNotFoundException("Image not found wit this id: "+id);
		});
	}

	@Override
	public List<ImageDto> saveImages(List<MultipartFile> files, Long ProductId) {
		Product product=productService.getProductById(ProductId);
		List<ImageDto> savedImageDto=new ArrayList<>();
			for (MultipartFile file : files) {
				try {
					Image image=new Image();
					image.setFileName(file.getOriginalFilename());
					image.setFileType(file.getContentType());
					image.setImage(new SerialBlob(file.getBytes()));
					image.setProduct(product);
					
					String buildDownloadUrl="/api/v1/images/image/download/";
					String downLoadUrl=buildDownloadUrl+image.getId();
					image.setDownloadUrl(downLoadUrl);
					Image savedImage=imageRepository.save(image);
					
					savedImage.setDownloadUrl(buildDownloadUrl+savedImage.getId());
					imageRepository.save(savedImage);
					
					ImageDto imageDto=new ImageDto();
					imageDto.setImageId(savedImage.getId());
					imageDto.setImageName(savedImage.getFileName());
					imageDto.setDownloadUrl(savedImage.getDownloadUrl());
					savedImageDto.add(imageDto);
				} catch (IOException | SQLException e) {
					throw new RuntimeException(e.getMessage());
				}
			}
		
		return savedImageDto;
	}

	@Override
	public void updateImage(MultipartFile file, Long imageId) {
		Image image =getImageById(imageId);
		
		try {
			image.setFileName(file.getOriginalFilename());
			image.setFileType(file.getContentType());
			image.setImage(new SerialBlob(file.getBytes()));
			imageRepository.save(image);
		} catch (IOException | SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}

}
