package co.edu.udea.easymanager.sale.io.web.v1;

import co.edu.udea.easymanager.shared.model.ErrorDetails;
import co.edu.udea.easymanager.shared.model.ResponsePagination;
import co.edu.udea.easymanager.sale.io.web.v1.model.SaleSaveRequest;
import co.edu.udea.easymanager.sale.io.web.v1.model.SaleSaveResponse;
import co.edu.udea.easymanager.sale.io.web.v1.model.SaleListResponse;
import co.edu.udea.easymanager.sale.model.Sale;
import co.edu.udea.easymanager.sale.model.SaleProduct;
import co.edu.udea.easymanager.sale.service.SaleService;
import co.edu.udea.easymanager.sale.service.model.SaleSaveCmd;

import co.edu.udea.easymanager.closeout.model.Closeout;
import co.edu.udea.easymanager.closeout.service.CloseoutService;
import co.edu.udea.easymanager.closeout.service.CloseoutServiceImpl;
import co.edu.udea.easymanager.closeout.io.web.v1.model.CloseoutListResponse;

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
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

@RestController
@RequestMapping(path = "/api/v1/sales", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = {"Sales"}, value = "Sales")
public class SaleController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SaleService saleService;
    private CloseoutService closeoutService;

    @GetMapping
    @ApiOperation(value = "Find sales", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success", response = SaleListResponse.class),
        @ApiResponse(code = 400, message = "Payload is invalid.", response = ErrorDetails.class),
        @ApiResponse(code = 500, message = "Internal server error.", response = ErrorDetails.class)})
    public ResponseEntity<List<SaleListResponse>> findAll() {
        List<Sale> salesFound = saleService.findAllFetchSales();
        List<SaleListResponse> salesFoundList = salesFound.stream().map(SaleListResponse::fromModel)
                .collect(Collectors.toList());
        logger.debug("End findByParameters: usersFound = {}", salesFound);
        return new ResponseEntity<List<SaleListResponse>>(salesFoundList, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Create a Sale.", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Created."),
            @ApiResponse(code = 400, message = "Payload is invalid.", response = ErrorDetails.class),
            @ApiResponse(code = 404, message = "Resource not found.", response = ErrorDetails.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ErrorDetails.class)
    })
    @ResponseStatus(value = HttpStatus.CREATED)
    @CrossOrigin(exposedHeaders = {HttpHeaders.LOCATION})
    public ResponseEntity<Void> create(@Valid @NotNull @RequestBody SaleSaveRequest saleToCreate) {
        logger.debug("Begin create: saleToCreate = {}", saleToCreate);
        SaleSaveCmd saleToCreateCmd = SaleSaveRequest.toModel(saleToCreate);
        Sale saleCreated = saleService.create(saleToCreateCmd);
        URI location = fromUriString("/api/v1/sales").path("/{id}")
                .buildAndExpand(saleCreated.getId()).toUri();
        logger.debug("End create: saleCreated = {}", saleCreated);
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    @ApiOperation(value = "Find a Sales.", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success.", response = CloseoutListResponse.class),
            @ApiResponse(code = 400, message = "Payload is invalid.", response = ErrorDetails.class),
            @ApiResponse(code = 404, message = "Resource not found.", response = ErrorDetails.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ErrorDetails.class)
    })
    public ResponsePagination<CloseoutListResponse> findAll(@PageableDefault(page = 0, size = 10) 
                                                                Pageable pageable) {
        Page<Closeout> closeoutFound = closeoutService.findAll(pageable);
        List<CloseoutListResponse> closeoutFoundList = closeoutFound.stream()
                .map(CloseoutListResponse::fromModel)
                .collect(Collectors.toList());
        return ResponsePagination.fromObject(closeoutFoundList, closeoutFound.getTotalElements(), 
                closeoutFound.getNumber(), closeoutFoundList.size());
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Find a Sale by id.", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success.", response = SaleSaveResponse.class),
            @ApiResponse(code = 400, message = "Payload is invalid.", response = ErrorDetails.class),
            @ApiResponse(code = 404, message = "Resource not found.", response = ErrorDetails.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ErrorDetails.class)
    })
    public ResponseEntity<SaleSaveResponse> findById(@Valid @PathVariable("id") @NotNull Long id) {
        logger.debug("Begin findById: id = {}", id);
        Sale saleFound = saleService.findById(id);
        SaleSaveResponse saleToResponse = SaleSaveResponse.fromModel(saleFound);
        logger.debug("End findById: saleFound = {}", saleToResponse);
        return ResponseEntity.ok(saleToResponse);
    }
}
