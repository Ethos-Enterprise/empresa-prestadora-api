create TABLE IF NOT EXISTS EMPRESA  (
  id uuid PRIMARY KEY,
  razao_social varchar(300) NOT NULL UNIQUE,
  cnpj varchar(14) NOT NULL UNIQUE,
  telefone varchar(14) UNIQUE,
  email varchar(300) NOT NULL UNIQUE,
  senha varchar(300) NOT NULL,
  setor varchar(300),
  qtd_funcionarios int,
  endereco_id UUID
 );

create TABLE IF NOT EXISTS ENDERECO  (
  id UUID PRIMARY KEY,
  numero varchar(5),
  cep varchar(10),
  logradouro varchar(200),
  complemento varchar(300),
  bairro varchar(300),
  uf char(2)
);

alter table empresa
add constraint fk_endereco_id
foreign key(endereco_id) references endereco(id);


create TABLE IF NOT EXISTS PRESTADORA_SERVICO (
  id uuid PRIMARY KEY,
  status_aprovacao varchar(30),
  fk_empresa UUID unique,
  foreign key(fk_empresa) references empresa(id)
);


-- Crie a função para retornar apenas o status_prestadora
CREATE OR REPLACE FUNCTION consultar_status_prestadora()
RETURNS TABLE (
    status_prestadora text
) AS $$
BEGIN
    RETURN QUERY
    SELECT
        CASE
            WHEN ps.fk_empresa IS NOT NULL THEN
                CASE
                    WHEN ps.status_aprovacao = 'APROVADO' THEN 'Empresa prestadora aprovada'
                    WHEN ps.status_aprovacao = 'PENDENTE' THEN 'Empresa prestadora pendente'
                    WHEN ps.status_aprovacao = 'REPROVADO' THEN 'Empresa prestadora reprovada'
                    ELSE 'Status desconhecido'
                END
            ELSE 'Não é uma empresa prestadora'
        END AS status_prestadora
    FROM
        empresa e
    LEFT JOIN
        prestadora_servico ps ON e.id = ps.fk_empresa;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION consultar_empresa_prestadora_por_id(p_id_empresa uuid)
RETURNS text AS $$
BEGIN
    RETURN (
        SELECT
            CASE
                WHEN ps.fk_empresa IS NOT NULL THEN
                    CASE
                        WHEN ps.status_aprovacao = 'APROVADO' THEN 'Empresa prestadora aprovada'
                        WHEN ps.status_aprovacao = 'PENDENTE' THEN 'Empresa prestadora pendente'
                        WHEN ps.status_aprovacao = 'REPROVADO' THEN 'Empresa prestadora reprovada'
                        ELSE 'Status desconhecido'
                    END
                ELSE 'Não é uma empresa prestadora'
            END AS status_prestadora
        FROM
            empresa e
        LEFT JOIN
            prestadora_servico ps ON e.id = ps.fk_empresa
        WHERE
            e.id = p_id_empresa
    );
END;
$$ LANGUAGE plpgsql;
select * from endereco e;
