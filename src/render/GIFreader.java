package render;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GIFreader {
	private static ClassLoader cl = GIFreader.class.getClassLoader();

	public static GIFBufferImage[] get(String url) {
		GIFBufferImage[] frame = null;
		try {
			ImageReader reader = ImageIO.getImageReadersByFormatName("gif").next();
			ImageInputStream stream = ImageIO.createImageInputStream(cl.getResourceAsStream(url));
			reader.setInput(stream);
			int count = reader.getNumImages(true);
			frame = new GIFBufferImage[count];

			for (int i = 0; i < count; i++) {
				BufferedImage image = reader.read(i);
				frame[i] = new GIFBufferImage(image);
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
			/*
			 * sword =
			 * ImageIO.read(cl.getResourceAsStream("pic/anime/sword.png"));
			 * sword1 =
			 * ImageIO.read(cl.getResourceAsStream("pic/anime/sword1.png"));
			 * sword2 =
			 * ImageIO.read(cl.getResourceAsStream("pic/anime/sword2.png"));
			 * shiled =
			 * ImageIO.read(cl.getResourceAsStream("pic/anime/shiled.png"));
			 * shiled1 =
			 * ImageIO.read(cl.getResourceAsStream("pic/anime/shiled1.png"));
			 * shiled2 =
			 * ImageIO.read(cl.getResourceAsStream("pic/anime/shiled2.png"));
			 * bin = ImageIO.read(cl.getResourceAsStream("pic/anime/bin.png"));
			 * ImageReader reader =
			 * ImageIO.getImageReadersByFormatName("gif").next();
			 * ImageInputStream kingburny = ImageIO
			 * .createImageInputStream(cl.getResourceAsStream(
			 * "pic/anime/KingBurny.gif")); ImageInputStream attackkingburny =
			 * ImageIO .createImageInputStream(cl.getResourceAsStream(
			 * "pic/anime/AttackKingBurny.gif")); ImageInputStream burny =
			 * ImageIO.createImageInputStream(cl.getResourceAsStream(
			 * "pic/anime/burny.gif")); ImageInputStream attackburny = ImageIO
			 * .createImageInputStream(cl.getResourceAsStream(
			 * "pic/anime/attackburny.gif")); ImageInputStream megaburny =
			 * ImageIO .createImageInputStream(cl.getResourceAsStream(
			 * "pic/anime/megaburny.gif")); ImageInputStream attackmegaburny =
			 * ImageIO .createImageInputStream(cl.getResourceAsStream(
			 * "pic/anime/attackmegaburny.gif")); ImageInputStream gunner =
			 * ImageIO.createImageInputStream(cl.getResourceAsStream(
			 * "pic/anime/gunner.gif")); ImageInputStream attackgunner = ImageIO
			 * .createImageInputStream(cl.getResourceAsStream(
			 * "pic/anime/attackgunner.gif")); ImageInputStream gunner2 =
			 * ImageIO.createImageInputStream(cl.getResourceAsStream(
			 * "pic/anime/gunner2.gif")); ImageInputStream attackgunner2 =
			 * ImageIO .createImageInputStream(cl.getResourceAsStream(
			 * "pic/anime/attackgunner2.gif")); ImageInputStream duel =
			 * ImageIO.createImageInputStream(cl.getResourceAsStream(
			 * "pic/anime/duel.gif")); ImageInputStream attackduel = ImageIO
			 * .createImageInputStream(cl.getResourceAsStream(
			 * "pic/anime/attackduel.gif")); ImageInputStream duel2 =
			 * ImageIO.createImageInputStream(cl.getResourceAsStream(
			 * "pic/anime/duel2.gif")); ImageInputStream attackduel2 = ImageIO
			 * .createImageInputStream(cl.getResourceAsStream(
			 * "pic/anime/attackduel2.gif")); ImageInputStream sniper =
			 * ImageIO.createImageInputStream(cl.getResourceAsStream(
			 * "pic/anime/sniper.gif")); ImageInputStream attacksniper = ImageIO
			 * .createImageInputStream(cl.getResourceAsStream(
			 * "pic/anime/attacksniper.gif")); ImageInputStream minimaxwell =
			 * ImageIO .createImageInputStream(cl.getResourceAsStream(
			 * "pic/anime/minimaxwell.gif")); ImageInputStream attackminimaxwell
			 * = ImageIO .createImageInputStream(cl.getResourceAsStream(
			 * "pic/anime/attackminimaxwell.gif")); ImageInputStream maxwell =
			 * ImageIO.createImageInputStream(cl.getResourceAsStream(
			 * "pic/anime/maxwell.gif")); ImageInputStream attackmaxwell =
			 * ImageIO .createImageInputStream(cl.getResourceAsStream(
			 * "pic/anime/attackmaxwell.gif")); ImageInputStream tilith =
			 * ImageIO.createImageInputStream(cl.getResourceAsStream(
			 * "pic/anime/tilith.gif")); ImageInputStream attacktilith = ImageIO
			 * .createImageInputStream(cl.getResourceAsStream(
			 * "pic/anime/attacktilith.gif")); reader.setInput(kingburny);
			 */

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return frame;
	}

}
