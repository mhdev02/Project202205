// https://blog.taeseong.me/329
// https://abbo.tistory.com/122
// https://stackoverflow.com/questions/49665735/spring-mvc-image-uploading-and-imageio-conversion-fails-on-linux
package com.project.backend.common;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageUtils {

	int ABSOLUTE_WIDTH = 415;
	int ABSOLUTE_HEIGHT = 0;

	public byte[] imageResize(MultipartFile srcFile) throws IOException {

		BufferedImage originalImage = ImageIO.read(srcFile.getInputStream());
		int originalWidth = originalImage.getWidth();
		int originalHeight = originalImage.getHeight();
		double ratio = ((double) originalHeight) / originalWidth;
		ABSOLUTE_HEIGHT = (int)Math.round(ABSOLUTE_WIDTH * ratio);
		
		int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
		BufferedImage resizeImage = resizeImageWithHint(originalImage, type);

		final ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();

		ImageIO.write(resizeImage, "jpg", byteArrayOut);

		final byte[] resultBytes = byteArrayOut.toByteArray();

		return resultBytes;
	}

	private BufferedImage resizeImageWithHint(BufferedImage originalImage, int type) {

		BufferedImage resizedImage = new BufferedImage(ABSOLUTE_WIDTH, ABSOLUTE_HEIGHT, type);
		Graphics2D graphics2D = resizedImage.createGraphics();
		graphics2D.drawImage(originalImage, 0, 0, ABSOLUTE_WIDTH, ABSOLUTE_HEIGHT, null);
		graphics2D.dispose();
		graphics2D.setComposite(AlphaComposite.Src);

		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		return resizedImage;
	}

	public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
		File convFile = new File(multipart.getOriginalFilename());
		multipart.transferTo(convFile);
		return convFile;
	}

}
