create TABLE IF NOT EXISTS PRESTADORA_SERVICO (
  id uuid PRIMARY KEY,
  status_aprovacao varchar(30),
  fk_empresa UUID unique
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
