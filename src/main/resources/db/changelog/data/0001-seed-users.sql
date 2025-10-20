--liquibase formatted sql

--changeset sga:data-0001
INSERT INTO users (id, username, email, password, created_at, updated_at) VALUES
('550e8400-e29b-41d4-a716-446655440001', 'admin', 'admin@news.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi', NOW(), NOW()),
('550e8400-e29b-41d4-a716-446655440002', 'user1', 'user1@news.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi', NOW(), NOW()),
('550e8400-e29b-41d4-a716-446655440003', 'user2', 'user2@news.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsМ8lE9lBOsl7iKTVEFDi', NOW(), NOW())
ON CONFLICT (id) DO UPDATE SET
    username = EXCLUDED.username,
    email = EXCLUDED.email,
    password = EXCLUDED.password,
    updated_at = EXCLUDED.updated_at;


