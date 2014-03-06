package br.gov.frameworkdemoiselle.resteasy.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.method.MethodConstraintViolation;
import org.hibernate.validator.method.MethodConstraintViolationException;

public class ValidationException extends javax.validation.ValidationException {

	private static final long serialVersionUID = 1L;

	private Set<Violation> violations = new HashSet<Violation>();

	public ValidationException() {
	}

	public static ValidationException parse(MethodConstraintViolationException exception) {
		ValidationException instance = new ValidationException();
		instance.addViolations(exception.getConstraintViolations());

		return instance;
	}

	public ValidationException addViolation(String property, String message) {
		this.violations.add(new Violation(property, message));
		return this;
	}

	public ValidationException addViolations(Set<MethodConstraintViolation<?>> violations) {
		for (MethodConstraintViolation<?> violation : violations) {
			this.violations.add(Violation.parse(violation));
		}
		return this;
	}

	public Set<Violation> getConstraintViolations() {
		return violations;
	}

	public static class Violation {

		public String property;

		public String message;

		public Violation() {
		}

		public static Violation parse(MethodConstraintViolation<?> violation) {
			return new Violation(getProperty(violation), violation.getMessage());
		}

		private static String getProperty(MethodConstraintViolation<?> violation) {
			String parts[] = violation.getPropertyPath().toString().split("\\.|\\[|\\]\\.");
			String property = null;

			if (parts.length > 1) {
				property = parts[1];

				for (String part : Arrays.copyOfRange(parts, 2, parts.length)) {
					property += "." + part;
				}
			}

			return property;
		}

		public Violation(String property, String message) {
			this.property = property;
			this.message = message;
		}

		public String getProperty() {
			return property;
		}

		public void setProperty(String property) {
			this.property = property;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((message == null) ? 0 : message.hashCode());
			result = prime * result + ((property == null) ? 0 : property.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Violation other = (Violation) obj;
			if (message == null) {
				if (other.message != null)
					return false;
			} else if (!message.equals(other.message))
				return false;
			if (property == null) {
				if (other.property != null)
					return false;
			} else if (!property.equals(other.property))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return this.property + " " + this.message;
		}
	}
}
