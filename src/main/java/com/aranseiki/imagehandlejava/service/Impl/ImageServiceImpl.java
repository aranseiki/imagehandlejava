package com.aranseiki.imagehandlejava.service.Impl;

import com.aranseiki.imagehandlejava.service.ImageService;
import com.aranseiki.imagehandlejava.model.dto.ImageRequest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class ImageServiceImpl implements ImageService {

    @Override
    public ResponseEntity<String> resizeImage(ImageRequest request) {
        // Validar os dados da requisição
        ResponseEntity<String> validationResult = validateRequest(request);

        if (validationResult != null) {
            return validationResult;
        }

        // Implementar a lógica de manipulação de imagem aqui
        String resizedImageBase64 = resizeImage(
            request.getImagemAtualBase64(), 
            request.getResolucaoHorizontal(),
            request.getResolucaoVertical()
        );

        // Salvar a imagem redimensionada no arquivo especificado
        boolean saveSuccess = saveImageToFile(resizedImageBase64, request.getCaminhoImagemNova());

        // Verificar se a operação de salvamento foi bem-sucedida
        if (saveSuccess) {
            return new ResponseEntity<>("Imagem redimensionada e salva com sucesso em " + request.getCaminhoImagemNova(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Falha ao salvar a imagem em " + request.getCaminhoImagemNova(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método privado para redimensionar a imagem
    private String resizeImage(String imageBase64, int newWidth, int newHeight) {
        try {
            // Converte a string base64 de volta para bytes
            byte[] imageData = Base64.getDecoder().decode(imageBase64);

            // Cria um BufferedImage a partir dos bytes da imagem
            ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
            BufferedImage originalImage = ImageIO.read(bis);
            bis.close();

            // Redimensiona a imagem para as novas dimensões
            Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);
            BufferedImage bufferedResizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            bufferedResizedImage.getGraphics().drawImage(resizedImage, 0, 0, null);

            // Converte a imagem redimensionada para bytes em formato JPG
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bufferedResizedImage, "jpg", bos);

            // Converte os bytes da imagem redimensionada para uma string base64
            byte[] resizedImageData = bos.toByteArray();
            String resizedImageBase64 = Base64.getEncoder().encodeToString(resizedImageData);

            // Fecha o ByteArrayOutputStream
            bos.close();

            return resizedImageBase64;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método privado para validar os dados da requisição
    private ResponseEntity<String> validateRequest(ImageRequest request) {
        if (request == null) {
            return new ResponseEntity<>("Requisição inválida: o corpo da requisição está vazio",
                    HttpStatus.BAD_REQUEST);
        }
        // Valide outros campos da requisição conforme necessário
        return null; // Retorna null se a validação for bem-sucedida
    }

    // Método privado para salvar a imagem em arquivo
    private boolean saveImageToFile(String imageBase64, String destinationPath) {
        try {
            // Converte a string base64 de volta para bytes
            byte[] imageData = Base64.getDecoder().decode(imageBase64);

            // Cria um ByteArrayInputStream a partir dos bytes da imagem
            ByteArrayInputStream bis = new ByteArrayInputStream(imageData);

            // Lê a imagem a partir do ByteArrayInputStream
            BufferedImage bufferedImage = ImageIO.read(bis);

            // Fecha o ByteArrayInputStream
            bis.close();

            // Obtém a extensão do arquivo da string de destino
            String fileExtension = destinationPath.substring(destinationPath.lastIndexOf(".") + 1);

            // Salva a imagem no arquivo no destino especificado
            File outputFile = new File(destinationPath);
            ImageIO.write(bufferedImage, fileExtension, outputFile);

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
