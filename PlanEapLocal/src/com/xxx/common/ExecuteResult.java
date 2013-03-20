package com.xxx.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Goma oma1989@yeah.net DESC:This class is used to store the returned
 *         results and successful message, or an error message.
 * @param <T>
 */
public class ExecuteResult<T> {
	private T result;
	private String status;
	private String successMessage;
	private List<String> errorMessages = null;
	private Map<String, String> fieldErrors = null;
	private List<String> warningMessages = null;
	private Map<String, Object> otherParam = null;

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public boolean isSuccess() {
		return ((this.errorMessages == null && this.fieldErrors == null) || (this.errorMessages.isEmpty())
				&& (this.fieldErrors.isEmpty() && status == "" ));
	}

	public String getAllErrorMessages() {
		StringBuffer msg = new StringBuffer();
		if (this.status != null)
			msg.append("STATUS:").append(this.status).append("\n");
		if (this.errorMessages != null && errorMessages.size() > 0) {
			msg.append("ERROR:").append("\n");
			for (String error : errorMessages)
				msg.append(error).append("\n");
		}
		if (this.fieldErrors != null) {
			Iterator<String> keys = this.fieldErrors.keySet().iterator();
			msg.append("Field Error:").append("\n");
			while (keys.hasNext()) {
				String key = keys.next();
				msg.append(key).append(":").append(this.fieldErrors.get(key))
						.append("\n");
			}
		}
		return msg.toString();
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public List<String> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}

	public Map<String, String> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(Map<String, String> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

	public List<String> getWarningMessages() {
		return warningMessages;
	}

	public void setWarningMessages(List<String> warningMessages) {
		this.warningMessages = warningMessages;
	}

	public void addErrorMessage(String errorMessage) {
		synchronized (this) {
			if (this.errorMessages == null) {
				this.errorMessages = new ArrayList<String>();
			}
		}
		this.errorMessages.add(errorMessage);
	}

	public void addFieldError(String field, String errorMessage) {
		synchronized (this) {
			if (this.fieldErrors == null) {
				this.fieldErrors = new HashMap<String, String>();
			}
		}
		this.fieldErrors.put(field, errorMessage);
	}

	public void addWarningMessage(String warningMessage) {
		synchronized (this) {
			if (this.warningMessages == null) {
				this.warningMessages = new ArrayList<String>();
			}
		}
		this.warningMessages.add(warningMessage);
	}

	public Map<String, Object> getOtherParam() {
		return otherParam;
	}

	public void setOtherParam(Map<String, Object> otherParam) {
		this.otherParam = otherParam;
	}

	public void addOtherParam(String key, Object obj) {
		synchronized (this) {
			if (this.otherParam == null) {
				this.otherParam = new HashMap<String, Object>();
			}
		}
		this.otherParam.put(key, obj);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
