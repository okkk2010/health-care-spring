package inhatc.cse.inhive.recording.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import inhatc.cse.inhive.exceptions.BadRequestException;
import inhatc.cse.inhive.response.Response;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Builder
@Service
@RequiredArgsConstructor
@Slf4j
public class RecordingService {
    public Response<Map<String, Object>> upload(String uploadDir, MultipartFile file, String appointmentCode)
    {
        if(file.isEmpty()) {
            throw new BadRequestException("Empty file.");
        }

        try {
            Path dir = Paths.get(uploadDir, appointmentCode);
            Files.createDirectories(dir);

            String ext = ".webm";
            String original = StringUtils.cleanPath(file.getOriginalFilename() == null ? "" : file.getOriginalFilename());

            if(original.lastIndexOf('.') > -1) {
                ext = original.substring(original.lastIndexOf('.'));
            }

            String savedName = UUID.randomUUID() + ext;
            Path target = dir.resolve(savedName);
            log.info("target={}", target.toAbsolutePath());
            log.info("size={}, type={}", file.getSize(), file.getContentType());

            InputStream in = file.getInputStream();
            Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);

            // file.transferTo(target.toFile());

            return Response.<Map<String, Object>>builder()
                    .statusCode(200)
                    .message("Upload success")
                    .data(Map.of(
                        "fileName", savedName,
                        "path", target.toString(),
                        "size", file.getSize(),
                        "contentType", file.getContentType()
                    ))
                    .build();
        } catch (IOException e ) {
            throw new RuntimeException("Failed to create recording directory", e);
        } catch (IllegalStateException e ) {
            log.error("transferTo IOException ", e);
            throw new RuntimeException("녹음파일 저장 실패");
        }
    }
}
