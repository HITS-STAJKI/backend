INSERT INTO languages (id, name) VALUES
                                     (gen_random_uuid(), 'Java'),
                                     (gen_random_uuid(), 'C#'),
                                     (gen_random_uuid(), 'Python'),
                                     (gen_random_uuid(), 'JavaScript'),
                                     (gen_random_uuid(), 'TypeScript'),
                                     (gen_random_uuid(), 'Ruby'),
                                     (gen_random_uuid(), 'Go'),
                                     (gen_random_uuid(), 'PHP'),
                                     (gen_random_uuid(), 'Swift'),
                                     (gen_random_uuid(), 'Kotlin'),
                                     (gen_random_uuid(), 'Rust')
ON CONFLICT (name) DO NOTHING;

INSERT INTO stacks (id, name) VALUES
                                  (gen_random_uuid(), 'Backend'),
                                  (gen_random_uuid(), 'Frontend'),
                                  (gen_random_uuid(), 'Android'),
                                  (gen_random_uuid(), 'IOS'),
                                  (gen_random_uuid(), 'Analyst'),
                                  (gen_random_uuid(), 'ML')
ON CONFLICT (name) DO NOTHING;
