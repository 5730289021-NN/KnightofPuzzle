package render;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import res.ResourceException;

public class ImageReader {
	private static ClassLoader cl = ImageReader.class.getClassLoader();

	public static ImageData[] get(String url) throws ResourceException {
		
		String extension = url.substring(url.length()-3,url.length());
		
		if(extension.equals("gif")) {
			
			ImageData[] frame = null;
			try {
				javax.imageio.ImageReader reader = ImageIO.getImageReadersByFormatName("gif").next();
				ImageInputStream stream = ImageIO.createImageInputStream(cl.getResourceAsStream(url));
				reader.setInput(stream);
				int count = reader.getNumImages(true);
				frame = new ImageData[count];
	
				for (int i = 0; i < count; i++) {
					BufferedImage image = reader.read(i);
					frame[i] = new ImageData(image);
					NodeList child = reader.getImageMetadata(i).getAsTree("javax_imageio_gif_image_1.0").getChildNodes();
	
					for (int j = 0; j < child.getLength(); j++) {
						Node nodeItem = child.item(j);
						if (nodeItem.getNodeName().equals("ImageDescriptor")) {
							int offsetX = Integer
									.valueOf(nodeItem.getAttributes().getNamedItem("imageLeftPosition").getNodeValue());
							int offsetY = Integer
									.valueOf(nodeItem.getAttributes().getNamedItem("imageTopPosition").getNodeValue());
							frame[i].setOffset(offsetX, offsetY);
							break;
						}
					}
	
				}

	
			} catch (IOException ex) {
				throw new ResourceException(ResourceException.IOException);
			}
			return frame;
			
		} else if(extension.equals("png") || extension.equals("jpg")) {
			ImageData[] image = new ImageData[1];
			
			try {
				image[0] = new ImageData(ImageIO.read(cl.getResource(url)));
				return image;
			} catch(IOException e) {
				throw new ResourceException(ResourceException.IOException);
			}
		} else {
			throw new ResourceException(ResourceException.IMAGE_EXTENSION_INCORRECT);
		}
	}

}
