INSERT INTO public.chat_user (id, display_name, password, username) VALUES (-1, 'Osman', 'admin', 'admin');

INSERT INTO public.chat_permissions (ID,permission_name,key) VALUES (1,'Create User','CREATE_USER');
INSERT INTO public.chat_permissions (ID,permission_name,key) VALUES (2,'Create Board','CREATE_BOARD');
INSERT INTO public.chat_permissions (ID,permission_name,key) VALUES (3,'Post Messages','POST_MESSAGE');

INSERT INTO public.chat_user_user_permissions (chat_user_id, user_permissions_id) VALUES (-1, 1);
INSERT INTO public.chat_user_user_permissions (chat_user_id, user_permissions_id) VALUES (-1, 2);
INSERT INTO public.chat_user_user_permissions (chat_user_id, user_permissions_id) VALUES (-1, 3);

INSERT INTO public.chat_board (id, description, board_name) VALUES (-1, 'Sample board', 'Java Coding');
INSERT INTO public.chat_board (id, description, board_name) VALUES (-2, 'Another Board', 'C# Coding');