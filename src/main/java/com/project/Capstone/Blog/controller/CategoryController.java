spackage com.project.Capstone.blog.controller;

import com.project.Capstone.blog.dto.request.CategoryRequest;
import com.project.Capstone.blog.dto.response.CategoryResponse;
import com.project.Capstone.blog.service.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j; // ✅ Logger import
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blog/categories")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Slf4j // ✅ Enables log.info, log.warn, etc.
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@RequestBody CategoryRequest request) {
        log.info("Creating new blog category: {}", request.getName());
        CategoryResponse response = categoryService.create(request);
        log.info("Blog category created with ID: {}", response.getId());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAll() {
        log.info("Fetching all blog categories");
        List<CategoryResponse> categories = categoryService.getAll();
        log.info("Total categories fetched: {}", categories.size());
        return ResponseEntity.ok(categories);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Deleting blog category with ID: {}", id);
        categoryService.delete(id);
        log.info("Blog category deleted with ID: {}", id);
        return ResponseEntity.noContent().build();
    }
}
