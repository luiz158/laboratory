create table demoiselle_EventoConfiguracao (
	eventoConfiguracaoId LONG not null primary key,
	companyId LONG,
	userId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	eventoId LONG,
	abertoAoPublico BOOLEAN,
	cidadeDoEvento VARCHAR(75) null
);

create table demoiselle_EventoConvite (
	eventoConviteId LONG not null primary key,
	companyId LONG,
	userId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	eventoId LONG,
	textoConvite VARCHAR(75) null
);

create table demoiselle_EventoParticipante (
	eventoParticipanteId LONG not null primary key,
	companyId LONG,
	userId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	eventoId LONG,
	nome VARCHAR(75) null,
	email VARCHAR(75) null,
	instituicaoEmpresa VARCHAR(75) null,
	conviteEnviado BOOLEAN,
	certificadoImpresso BOOLEAN,
	inscricaoConfirmada BOOLEAN,
	participacaoConfirmada BOOLEAN
);