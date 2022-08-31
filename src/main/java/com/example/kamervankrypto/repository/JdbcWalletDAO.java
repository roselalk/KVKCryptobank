package com.example.kamervankrypto.repository;
import com.example.kamervankrypto.model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcWalletDAO implements WalletDAO {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcWalletDAO (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Wallet> findAllByTraderId(int id) {
        String sql = "Select * from Wallet Where idTrader = ?;";
        return jdbcTemplate.query(sql, new WalletRowMapper(), id);
    }

    public Optional<Wallet> findByTraderIdAndAsset(int id, String ticker) {
        String sql = "Select * from Wallet Where idTrader = ? AND Ticker = ?;";
        List<Wallet> resultList = jdbcTemplate.query(sql, new WalletRowMapper(), id, ticker);
        if (resultList.size() == 0) {
            return Optional.empty();
        } else {
            return Optional.of(resultList.get(0));
        }
    }
    @Override
    public void save(Wallet wallet) {
        String sql = "insert into Wallet (idTrader, Ticker, Amount) values (?, ?, ?)";
        jdbcTemplate.update(sql, wallet.getTrader().getID(), wallet.getAsset().getTicker(), wallet.getAmount());
    }

    @Override
    public void update(Wallet wallet) {
        String sql = "update Wallet set Amount = ? where idTrader = ? AND Ticker = ?;";
        jdbcTemplate.update(sql, wallet.getAmount(), wallet.getTrader().getID(), wallet.getAsset().getTicker());
    }

    //todo: we might not need this method, but it is here for CRUD functionality
    @Override
    public void delete(Wallet wallet) {
        String sql = "delete from wallet where idTrader = ? AND Ticker = ?;";
        jdbcTemplate.update(sql, wallet.getTrader().getID(), wallet.getAsset().getTicker());
    }
    private class WalletRowMapper implements RowMapper<Wallet> {
        @Override
        public Wallet mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            return new Wallet(resultSet.getInt("IdWallet"), resultSet.getDouble("Amount"));
        }
    }
}

