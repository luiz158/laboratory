create index IX_763B5020 on demoiselle_EventoConfiguracao (eventoId);

create index IX_DF335B29 on demoiselle_EventoConvite (eventoId);

create index IX_E799122D on demoiselle_EventoParticipante (email);
create index IX_3DFEF183 on demoiselle_EventoParticipante (eventoId);
create index IX_32255069 on demoiselle_EventoParticipante (eventoId, email);
create index IX_C46111CC on demoiselle_EventoParticipante (nome);