CREATE TABLE account
(
  account_id INT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE transfer
(
  transfer_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  transfer_amount DECIMAL(19,0) NOT NULL, -- can be changed using different database
  transfer_from INT NOT NULL REFERENCES account (account_id),
  transfer_to INT NOT NULL REFERENCES account (account_id)
);

CREATE INDEX idx_transfer_from ON transfer (transfer_from);

CREATE INDEX idx_transfer_to ON transfer (transfer_to);
