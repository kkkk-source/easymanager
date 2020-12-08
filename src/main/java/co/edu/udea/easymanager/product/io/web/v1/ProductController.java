package co.edu.udea.easymanager.product.io.web.v1;

import co.edu.udea.easymanager.shared.model.ErrorDetails;
import co.edu.udea.easymanager.shared.model.ResponsePagination;

import co.edu.udea.easymanager.product.io.web.v1.model.ProductSaveRequest;
import co.edu.udea.easymanager.product.io.web.v1.model.ProductSaveResponse;
import co.edu.udea.easymanager.product.model.Product;
import co.edu.udea.easymanager.product.service.ProductService;
import co.edu.udea.easymanager.product.service.model.ProductSaveCmd;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

@RestController
@RequestMapping(path = "/api/v1/products", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = {"Products"}, value = "Products")
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ApiOperation(value = "Create a Product.", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created."),
            @ApiResponse(code = 400, message = "Payload is invalid.", response = ErrorDetails.class),
            @ApiResponse(code = 404, message = "Resource not found.", response = ErrorDetails.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ErrorDetails.class)
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    @CrossOrigin(exposedHeaders = {HttpHeaders.LOCATION})
    public ResponseEntity<Void> create(@Valid @NotNull @RequestBody ProductSaveRequest productToCreate) {
        logger.debug("Begin create: productToCreate = {}", productToCreate);
        ProductSaveCmd productToCreateCmd = ProductSaveRequest.toModel(productToCreate);
        Product productCreated = productService.create(productToCreateCmd);
        URI location = fromUriString("/api/v1/products").path("/{id}")
                .buildAndExpand(productCreated.getId()).toUri();
        logger.debug("End create: productCreated = {}", productCreated);
        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Find a Product by id.", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success.", response = ProductSaveResponse.class),
            @ApiResponse(code = 400, message = "Payload is invalid.", response = ErrorDetails.class),
            @ApiResponse(code = 404, message = "Resource not found.", response = ErrorDetails.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ErrorDetails.class)
    })
    public ResponseEntity<ProductSaveResponse> findById(@Valid @PathVariable("id") @NotNull Long id) {
        logger.debug("Begin findById: id = {}", id);
        Product productFound = productService.findById(id);
        logger.debug("End findById: productFound = {}", productFound);
        return ResponseEntity.ok(ProductSaveResponse.fromModel(productFound));
    }


    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "Delete a Product.", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 204, message = "Success."),
            @ApiResponse(code = 400, message = "Payload is invalid.", response = ErrorDetails.class),
            @ApiResponse(code = 404, message = "Resource not found.", response = ErrorDetails.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ErrorDetails.class)

    })
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@Valid @PathVariable("id") @NotNull Long id) {
        logger.debug("Begin delete: id = {}", id);
        productService.deleteById(id);
        logger.debug("End delete: id = {}", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "Update a Product.", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success.", response = ProductSaveResponse.class),
            @ApiResponse(code = 400, message = "Payload is invalid.", response = ErrorDetails.class),
            @ApiResponse(code = 404, message = "Resource not found.", response = ErrorDetails.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ErrorDetails.class)

    })
    public ResponseEntity<ProductSaveResponse> update(@Valid @RequestBody @NotNull ProductSaveRequest productToUpdate,
                                                   @Valid @PathVariable("id") @NotNull Long id) {
        logger.debug("Begin update: productUpdate = {}, id = {}", productToUpdate, id);
        ProductSaveCmd productToUpdateCmd = ProductSaveRequest.toModel(productToUpdate);
        Product productUpdated = productService.update(id, productToUpdateCmd);
        logger.debug("End update: productUpdated = {}", productUpdated);
        return ResponseEntity.ok(ProductSaveResponse.fromModel(productUpdated));
    }
}
