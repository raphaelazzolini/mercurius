package br.unicamp.ic.lsd.mercurius.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.unicamp.ic.lsd.mercurius.datatype.Configuration;

import com.google.common.base.Objects;

@Entity(name = "Configuration")
@Table(name = "configuration")
public class ConfigurationImpl implements Configuration {

	private static final long serialVersionUID = 5349305590567462896L;

	protected ConfigurationImpl() {
		super();
	}

	@Id
	@Column(name = "key", nullable = false)
	private String key;

	@Column(name = "value", nullable = false)
	private String value;

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(super.hashCode(), key);
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof ConfigurationImpl) {
			ConfigurationImpl that = (ConfigurationImpl) object;
			return Objects.equal(this.key, that.key);
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ConfigurationImpl [key=");
		builder.append(key);
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}

}
