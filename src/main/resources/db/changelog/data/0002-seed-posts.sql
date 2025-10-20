--liquibase formatted sql

--changeset sga:data-0002
INSERT INTO posts (id, title, content, author_id, created_at, updated_at) VALUES
('650e8400-e29b-41d4-a716-446655440001', 'Java 21: основные изменения', 'Ключевые новшества: виртуальные потоки (Project Loom) для масштабируемой конкуррентности, шаблоны в switch и записи, улучшения производительности JVM и сборщиков мусора. В статье приводятся примеры миграции и рекомендации по внедрению в продакшн.', '550e8400-e29b-41d4-a716-446655440001', NOW() - INTERVAL '2 hours', NOW() - INTERVAL '2 hours'),
('650e8400-e29b-41d4-a716-446655440002', 'PostgreSQL 16 released', 'PostgreSQL 16 вышел с улучшенной производительностью, параллельной загрузкой данных, ускоренным планированием запросов и упрощённым управлением правами. Обновление добавляет новые функции для логической репликации, расширяет возможности JSON и улучшает мониторинг.', '550e8400-e29b-41d4-a716-446655440002', NOW() - INTERVAL '1 hour', NOW() - INTERVAL '1 hour'),
('650e8400-e29b-41d4-a716-446655440003', 'Lombok - это круто!', 'Lombok значительно упрощает написание Java кода, автоматически генерируя геттеры, сеттеры, конструкторы и многое другое.', '550e8400-e29b-41d4-a716-446655440001', NOW() - INTERVAL '30 minutes', NOW() - INTERVAL '30 minutes'),
('650e8400-e29b-41d4-a716-446655440004', 'Docker и контейнеризация', 'Docker позволяет легко упаковывать приложения в контейнеры, что упрощает развертывание и масштабирование.', '550e8400-e29b-41d4-a716-446655440003', NOW() - INTERVAL '15 minutes', NOW() - INTERVAL '15 minutes'),
('650e8400-e29b-41d4-a716-446655440005', 'PostgreSQL - надежная БД', 'PostgreSQL - это мощная, открытая система управления базами данных с отличной производительностью.', '550e8400-e29b-41d4-a716-446655440002', NOW() - INTERVAL '5 minutes', NOW() - INTERVAL '5 minutes')
ON CONFLICT (id) DO UPDATE SET
    title = EXCLUDED.title,
    content = EXCLUDED.content,
    author_id = EXCLUDED.author_id,
    updated_at = EXCLUDED.updated_at;


