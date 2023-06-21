package com.travel.travtronics.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.travel.travtronics.response.APIResponse;

@RestControllerAdvice
public class CustomExceptionalHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllUncaughtException(Exception exception, HttpServletRequest request) {
		String message = String.format("unknown error occurred : %s", exception.getLocalizedMessage());
		logger.error("unknown error occurred", exception);
		return buildResponseEntity(
				new APIResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), message, Collections.emptyList()), request,
				exception);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String message = "malformed json request..please check request body formatting";
		logger.error(ex.getMessage());
		return handleExceptionInternal(ex, new APIResponse(HttpStatus.BAD_REQUEST.value(), message, new ArrayList<>()),
				new HttpHeaders(), HttpStatus.CONFLICT, request);

	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> finalerrors = new ArrayList<>();
		List<String> feilderrors = ex.getBindingResult().getFieldErrors().stream()
				.map(er -> er.getField() + " : " + er.getDefaultMessage()).collect(Collectors.toList());
		finalerrors.addAll(feilderrors);
		List<String> gloabalerrors = ex.getBindingResult().getGlobalErrors().stream()
				.map(er -> er.getObjectName() + " : " + er.getDefaultMessage()).collect(Collectors.toList());
		finalerrors.addAll(gloabalerrors);
		logger.error(finalerrors);
		return handleExceptionInternal(ex, new APIResponse(HttpStatus.BAD_REQUEST.value(), "Validation Error",
				Collections.emptyList(), finalerrors), headers, status, request);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	private ResponseEntity<Object> handleMethodArgumentTypeMismatchException(final HttpServletRequest request,
			final MethodArgumentTypeMismatchException exception) {

		String message = String
				.format(exception.getName() + " should be of type " + exception.getRequiredType().getName());
		return buildResponseEntity(new APIResponse(HttpStatus.BAD_REQUEST.value(), message, Collections.emptyList()),
				request, exception);
	}

	@ExceptionHandler(javax.validation.ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolationException(final HttpServletRequest request,
			final ConstraintViolationException ex) {
		String message = "Property Violation Occurred";
		logger.error(ex.getCause().getMessage());

		List<String> violatedConstraints = ex.getConstraintViolations().stream()
				.map(cv -> cv.getRootBeanClass().getName() + " " + cv.getPropertyPath() + ":" + cv.getMessage())
				.collect(Collectors.toList());
		return buildResponseEntity(
				new APIResponse(HttpStatus.BAD_REQUEST.value(), message, Collections.emptyList(), violatedConstraints),
				request, ex);

	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> handleDataIntegrityViolatonException(final HttpServletRequest request,
			final DataIntegrityViolationException ex) {
		String message = "Property Violation Occurred";
		logger.error(ex.getCause().getMessage());
		return buildResponseEntity(new APIResponse(HttpStatus.BAD_REQUEST.value(), message, Collections.emptyList()),
				request, ex);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> details = new ArrayList<String>();
		details.add(ex.getParameterName() + " parameter is missing");
		return handleExceptionInternal(ex,
				new APIResponse(HttpStatus.BAD_REQUEST.value(), "Missing Parameters", Collections.emptyList(), details),
				headers, status, request);
	}

	private ResponseEntity<Object> buildResponseEntity(final APIResponse response, final HttpServletRequest request,
			final Exception exception) {
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
