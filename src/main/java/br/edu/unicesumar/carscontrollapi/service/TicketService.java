package br.edu.unicesumar.carscontrollapi.service;

import br.edu.unicesumar.carscontrollapi.domain.Ticket;
import br.edu.unicesumar.carscontrollapi.exceptions.DataIntegrityException;
import br.edu.unicesumar.carscontrollapi.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository repository;

    public Ticket saveTicketImage(MultipartFile file){
        try {
            validatedImage(file,false);
        }catch (Exception e){
            log.error("ImageService->saveExpenseImage {}",e.getMessage());
            throw new DataIntegrityException(e.getMessage());
        }
        String uploadDir = "uploads/ticket";
        return saveImageAndData(file, uploadDir);
    }

    private void validatedImage(MultipartFile file, boolean isAvatar) throws IOException{
        // Verifica se o arquivo é PNG ou JPG
        String contentType = file.getContentType();
        if (contentType == null || (!contentType.equals("image/png") && !contentType.equals("image/jpeg"))) {
            throw new IOException("A imagem deve ser PNG ou JPG!");
        }

        if (isAvatar){
            // Lê a imagem e verifica se é quadrada
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image.getWidth() != image.getHeight()) {
                throw new IOException("A imagem para avatar deve ser quadrada!");
            }
        }
    }
    private String saveImage(MultipartFile file, String uploadDir) throws IOException {
        // Cria o diretório de upload se não existir
        File directory = new File(uploadDir);
        if (!directory.exists()) directory.mkdirs();

        // Salva o arquivo
        String fileName = file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);
        Files.write(filePath, file.getBytes());

        return filePath.toString();
    }

    private Ticket saveImageAndData(MultipartFile file, String uploadDir){
        String filePath = null;
        try {
            filePath = saveImage(file, uploadDir);
        }catch (Exception e){
            log.error("ImageService->saveImageAndData {}",e.getMessage());
            throw new DataIntegrityException("Houve erro ao salvar a imagem");
        }
        try {
            var image = Ticket.builder().id(UUID.randomUUID()).path(filePath).build();
            return repository.save(image);
        }catch (Exception e){
            log.error("ImageService->saveData {}",e.getMessage());
            throw new DataIntegrityException("Houve erro ao salvar o caminho da imagem no banco de dados");
        }
    }

}
