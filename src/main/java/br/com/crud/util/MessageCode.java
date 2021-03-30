package br.com.crud.util;

public enum MessageCode {

    NOT_FOUND("ERRO-MSG-001"),
    BAD_REQUEST_SEARCH("ERRO-MSG-002"),
    BAD_REQUEST_CREATE("ERRO-MSG-003"),
    PERSIST_FAIL("ERRO-MSG-004"),
    UPDATE_FAIL("ERRO-MSG-005"),
    EDIT_FAIL("ERRO-MSG-006");

    private final String chave;

    /**
     * Construtor do Enum.
     *
     * @param chave
     */
    MessageCode(String chave) {
        this.chave = chave;
    }

    @Override
    public String toString() {
        return chave;
    }
}
